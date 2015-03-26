package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.Contest
import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}

object ContestDAO {

  val contests = TableQuery[models.database.Contests]

  def list(implicit ec: ExecutionContext) = Future {
    Try {
      DB.withSession { implicit session =>
        contests.list
      }
    }
  }

  def findById(id: Long)(implicit ec: ExecutionContext) = Future {
    Try {
      DB.withSession { implicit session =>
        contests.filter(_.id === id).firstOption
      }
    }
  }
}

