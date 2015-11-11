package com.brutehack.services

import javax.inject.Inject

import com.brutehack.domain.User
import com.github.racc.tscg.TypesafeConfig
import com.twitter.inject.Logging
import scalikejdbc._

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

    def apply(e: ResultName[User])(rs: WrappedResultSet): User =
      new User(
        id = rs.string(e.id),
        handle = rs.string(e.handle),
        password = rs.string(e.password),
        email = rs.string(e.email),
        firstName = rs.stringOpt(e.firstName),
        lastName = rs.stringOpt(e.lastName),
        rating = rs.int(e.rating),
        shirtSize = rs.stringOpt(e.shirtSize),
        location = rs.stringOpt(e.location)
      )
  }

  val usersSyntax = User.syntax

  def all(): Seq[User] = {
    DB readOnly { implicit session =>
      withSQL {
        selectFrom(User as usersSyntax)
      }.map(User(usersSyntax)).list().apply()
    }
  }

  def findBy(field: String)(value: String): Option[User] = {
    DB readOnly { implicit session =>
      withSQL {
        selectFrom(User as usersSyntax).where.eq(usersSyntax.column(field), value)
      }.map(User(usersSyntax)).toOption().apply()
    }
  }

  def findById(id: String): Option[User] = findBy("id")(id)

  def update() = ()

  def save(user: User): Int = 1

  def delete(handle: String): Int = {
    withSQL {
      deleteFrom(User).where.eq(User.column.handle, handle)
    }.update().apply()
  }
}
