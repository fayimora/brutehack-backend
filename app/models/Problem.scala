package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.{Problems, ContestsProblems}
import java.sql.Timestamp

case class Problem(author: String,
                   title: String,
                   description: String,
                   hint: String,
                   inputs: List[String],
                   outputs: List[String],
                   id: Option[Long],
                   createdAt: Option[Timestamp],
                   updatedAt: Option[Timestamp])


object Problem {

  lazy val database = Database.forDataSource(DB.getDataSource())
  val problems = TableQuery[Problems]
  val contestsProblems = TableQuery[ContestsProblems]

  def getProblemIds(cId: Long): List[Long] = {
    database withSession {implicit session =>
      contestsProblems.filter(_.contestId===cId).map(_.problemId).list
    }
  }

  def findByIDs(ids: List[Long]): List[Problem] = {
    database withTransaction { implicit session =>
      problems.filter(_.id inSet ids).list
    }
  }
}
