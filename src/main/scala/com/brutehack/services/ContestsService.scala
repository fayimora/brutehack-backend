package com.brutehack.services

import javax.inject.Inject

import com.brutehack.domain.Contest
import com.github.racc.tscg.TypesafeConfig
import com.twitter.inject.Logging
import scalikejdbc._

import scala.concurrent.{Future, ExecutionContext}

/**
 * Created by fayimora on 16/10/2015.
 */

class ContestsService @Inject()(
  @TypesafeConfig("db.driver") dbDriver: String,
  @TypesafeConfig("db.url") dbUrl: String,
  @TypesafeConfig("db.user") dbUser: String,
  @TypesafeConfig("db.password") dbPassword: String) extends Logging {

  Class.forName(dbDriver)
  ConnectionPool.singleton(dbUrl, dbUser, dbPassword)
  implicit val session = AutoSession

  object Contest extends SQLSyntaxSupport[Contest] {
    override def tableName = "contests"

    def apply(sp: SyntaxProvider[Contest])(rs: WrappedResultSet): Contest =
      apply(sp.resultName)(rs)

    def apply(e: ResultName[Contest])(rs: WrappedResultSet): Contest =
      new Contest(
        id = rs.string(e.id),
        title = rs.string(e.title),
        authorId = rs.string(e.authorId),
        description = rs.string(e.description),
        startTime = rs.jodaDateTimeOpt(e.startTime),
        duration = rs.string(e.duration),
        createdAt = rs.jodaDateTimeOpt(e.createdAt),
        updatedAt = rs.jodaDateTimeOpt(e.updatedAt)
      )
  }

  val contestsSyntax = Contest.syntax

  def all()(implicit ec: ExecutionContext): Future[Seq[Contest]] = Future {
    DB readOnly { implicit session =>
      withSQL {
        selectFrom(Contest as contestsSyntax)
      }.map(Contest(contestsSyntax)).list().apply()
    }
  }

  def findBy(field: String)(value: String)(implicit ec: ExecutionContext): Future[Option[Contest]] = Future {
    DB readOnly { implicit session =>
      withSQL {
        selectFrom(Contest as contestsSyntax).where.eq(contestsSyntax.column(field), value)
      }.map(Contest(contestsSyntax)).toOption().apply()
    }
  }

  def findById(id: String)(implicit ec: ExecutionContext): Future[Option[Contest]] =
    findBy("id")(id)

  def update()(implicit ec: ExecutionContext): Future[Unit] = Future(())

  def save()(implicit ec: ExecutionContext): Future[Unit] = Future(())

  def delete(id: String)(implicit ec: ExecutionContext): Future[Int] = Future {
    withSQL {
      deleteFrom(Contest).where.eq(Contest.column.id, id)
    }.update().apply()
  }
}
