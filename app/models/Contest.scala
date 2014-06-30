package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Contests
import java.sql.Timestamp

case class Contest(id: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp,
                   title: String,
                   author: String,
                   description: String,
                   startTime: Timestamp,
                   endTime: Timestamp,
                   problems: List[Int])

object Contest {

  lazy val database = Database.forDataSource(DB.getDataSource())
  val contests = TableQuery[Contests]

  def all(): List[Contest] = {
    database withTransaction { implicit session =>
      val cs = contests.list
      cs
    }
  }
}
