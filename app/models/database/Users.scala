package models.database

import models.User
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Users(tag: Tag) extends Table[User](tag, "users") with TimestampedTable {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.NotNull)
  def lastVisit = column[Timestamp]("last_visit", O.NotNull)
  def handle = column[String]("handle", O.NotNull)
  def firstName = column[String]("firstname")
  def lastName = column[String]("lastname")
  def email = column[String]("email", O.NotNull)
  def rating = column[Int]("rating", O.NotNull)
  def location = column[String]("location", O.NotNull)
  def shirtSize = column[String]("shirtsize", O.NotNull)

  def uniqueHandle = index("users_handle", handle, unique=true)
  def uniqueEmail = index("users_email", email, unique=true)

  def * = (lastVisit, handle, firstName, lastName, email, rating, location, shirtSize, id.?,
    createdAt.?, updatedAt.?) <> (User.tupled, User.unapply)
}

