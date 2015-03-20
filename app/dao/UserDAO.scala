package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.User
import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}

object UserDAO {

  val users = TableQuery[models.database.Users]

  def autoInc = users returning users

  def list(implicit ec: ExecutionContext) = Future{
    Try {
      DB.withSession{ implicit session =>
        users.list
      }
    }
  }


  def create(user: User)(implicit ec: ExecutionContext): Future[Try[User]] = Future {
    Try {
      DB.withSession { implicit session =>
        autoInc += user
      }
    }
  }


  def findById(id: Long)(implicit ec: ExecutionContext): Future[Try[Option[User]]] = Future {
    Try {
      DB.withSession { implicit session =>
        users.filter(_.id === id).firstOption
      }
    }
  }


  def findByHandle(handle: String)(implicit ec: ExecutionContext) = Future {
    Try {
      DB.withSession { implicit session =>
        users.filter(_.handle === handle).firstOption
      }
    }
  }

}

