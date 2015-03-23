package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.Problem
import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}

object ProblemDAO {

  val problems = TableQuery[models.database.Problems]
  val contestsProblems = TableQuery[models.database.ContestsProblems]

  def findByID(id: Long)(implicit ec: ExecutionContext) = Future {
    Try {
      DB.withSession { implicit session =>
        val res = problems.filter(_.id === id).firstOption
      }
    }
  }

  def findByIDs(ids: List[Long])(implicit ec: ExecutionContext) = Future {
    Try {
      DB.withSession { implicit session =>
        problems.filter(_.id inSet ids).list
      }
    }
  }
}

