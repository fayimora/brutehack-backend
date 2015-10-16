package com.brutehack.services

import javax.inject.Inject

import com.brutehack.domain.User
import com.github.racc.tscg.TypesafeConfig
import com.twitter.inject.Logging
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * Created by fayimora on 16/10/2015.
 */
class UsersService @Inject()(
  @TypesafeConfig("db.driver") dbDriver: String,
  @TypesafeConfig("db.url") dbUrl: String,
  @TypesafeConfig("db.user") dbUser: String,
  @TypesafeConfig("db.password") dbPassword: String) extends Logging {

  Class.forName(dbDriver)
  ConnectionPool.singleton(dbUrl, dbUser, dbPassword)
  implicit val session = AutoSession

  object User extends SQLSyntaxSupport[User] {
    override def tableName = "users"

    def apply(sp: SyntaxProvider[User])(rs: WrappedResultSet): User =
      apply(sp.resultName)(rs)

    def apply(e: ResultName[User])(rs: WrappedResultSet): User = new User(
      id = rs.string(e.id),
      handle = rs.string(e.handle),
      password = rs.string(e.password),
      email = rs.string(e.email),
      firstName = rs.string(e.firstName),
      lastName = rs.string(e.lastName),
      rating = rs.int(e.rating),
      shirtSize = rs.string(e.shirtSize),
      location = rs.string(e.location)
    )
  }

  val usersSyntax = User.syntax

  def all()(implicit ec : ExecutionContext): Future[Seq[User]] = Future {
    Seq(new User("","","","","","",0,"",""))
  }

  def findBy(field: String)(value: String)(implicit ec: ExecutionContext): Future[Option[User]] = Future {
    Option(new User("", "", "", "", "", "", 0, "", ""))
  }

  def findById(id: String)(implicit ec: ExecutionContext): Future[Option[User]] =
    findBy("id")(id)

  def update()(implicit ec: ExecutionContext): Future[Unit] = Future(())

  def save()(implicit ec: ExecutionContext): Future[Unit] = Future(())

  def delete()(implicit ec: ExecutionContext): Future[Unit] = Future(())
}
