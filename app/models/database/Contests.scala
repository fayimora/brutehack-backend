package models.database

import models.Contest
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Contests(tag: Tag) extends Table[Contest](tag, "CONTESTS") {
  def id = column[Long]("CONTEST_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def startTime = column[Timestamp]("START_TIME", O.NotNull)
  def endTime = column[Timestamp]("END_TIME", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("TEXT"))
  def problems = column[List[Int]]("PROBLEMS")

  def * = (id, createdAt, updatedAt, title, author, description, startTime, endTime, problems) <> (Contest.tupled, Contest.unapply)
}

object Contests {
  import play.api.db.DB
  import play.api.Play.current

  lazy val database = Database.forDataSource(DB.getDataSource())
  val contests = TableQuery[Contests]

  def all(): List[Contest] = {
    database withTransaction { implicit session =>
      val cs = contests.list
      cs
    }
  }
}
