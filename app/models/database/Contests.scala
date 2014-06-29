package models.database

import models.Contest
import scala.slick.driver.PostgresDriver.simple._
import java.sql.Timestamp

class Contests(tag: Tag) extends Table[Contest](tag, "CONTESTS") {
  def id = column[Long]("CONTEST_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def startTime = column[Timestamp]("START_TIME", O.NotNull)
  def endTime = column[Timestamp]("END_TIME", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("CLOB"))

  def * = (id, createdAt, updatedAt, title, author, description, startTime, endTime) <> (Contest.tupled, Contest.unapply)
}

