package models.database

import models.Contest
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Contests(tag: Tag) extends Table[Contest](tag, "contests") with TimestampedTable {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.NotNull)
  def startTime = column[Timestamp]("start_time", O.NotNull)
  def duration = column[String]("duration", O.NotNull)
  def title = column[String]("title", O.NotNull)
  def author = column[String]("author", O.NotNull)
  def description = column[String]("description", O.NotNull, O.DBType("TEXT"))

  def titleIndex = index("contest_title", title, unique=true)

  def * = (title, author, description, startTime, duration, id.?, createdAt.?, updatedAt.?) <>
  (Contest.tupled, Contest.unapply)
}

