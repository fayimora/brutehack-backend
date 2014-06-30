package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Contests
import java.sql.Timestamp
import play.Logger

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

  def findByID(id: Long): Option[Contest] = {
    database withTransaction { implicit session =>
      //TODO: Find a more efficient way to do this
      val res = contests.list.filter(_.id == id)
      // Logger.info(contests.list.mkString("\n"))
      res match {
        case c::Nil => Some(c)
        case _ => None
      }
    }
  }
}
