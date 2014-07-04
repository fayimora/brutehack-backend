package models.database

import models.User
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Users(tag: Tag) extends Table[User](tag, "USERS") {
  def id = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def lastVisit = column[Timestamp]("LAST_VISIT", O.NotNull)
  def handle = column[String]("HANDLE", O.NotNull)
  def firstName = column[String]("FIRSTNAME")
  def lastName = column[String]("LASTNAME")
  def email = column[String]("EMAIL", O.NotNull)
  def rating = column[Int]("RATING", O.NotNull)
  def location = column[String]("LOCATION", O.NotNull)
  def shirtSize = column[String]("SHIRTSIZE", O.NotNull)

  def * = (id, createdAt, updatedAt, lastVisit, handle, firstName, lastName, email, rating,
    location, shirtSize) <> ((User.apply _).tupled, User.unapply)
}

