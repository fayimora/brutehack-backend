package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Problems
import java.sql.Timestamp

case class Problem(id: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp,
                   author: String,
                   title: String,
                   description: String,
                   hint: String,
                   inputs: List[String],
                   outputs: List[String])


object Problem {

  lazy val database = Database.forDataSource(DB.getDataSource())
  val problems = TableQuery[Problems]

  def findByIDs(ids: List[Long]): List[Problem] = {
    database withTransaction { implicit session =>
      problems.filter(_.id inSet ids).list
    }
  }
}
