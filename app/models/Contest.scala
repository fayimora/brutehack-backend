package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Contests
import java.sql.Timestamp

case class Contest(title: String,
                   author: String,
                   description: String,
                   startTime: Timestamp,
                   duration: String,
                   id: Option[Long] = None,
                   createdAt: Option[Timestamp] = None,
                   updatedAt: Option[Timestamp] = None)


object Contest {
  lazy val database = Database.forDataSource(DB.getDataSource())
  val contests = TableQuery[Contests]

  def all(): List[Contest] = {
    database withTransaction { implicit session =>
      contests.list
    }
  }

  def findByID(id: Long): Option[Contest] = {
    database withTransaction { implicit session =>
      val res = contests.filter(_.id === id).list
      res match {
        case c::Nil => Some(c)
        case _ => None
      }
    }
  }
}
