package models.database

import models.Contest
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Contests(tag: Tag) extends Table[Contest](tag, "CONTESTS") with TimestampedTable {
  def id = column[Long]("CONTEST_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def startTime = column[Timestamp]("START_TIME", O.NotNull)
  def duration = column[String]("DURATION", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("TEXT"))

  def titleIndex = index("CONTEST_TITLE", title, unique=true)

  def * = (title, author, description, startTime, duration, id.?, createdAt.?, updatedAt.?) <>
  ((Contest.apply _).tupled, Contest.unapply)
}

