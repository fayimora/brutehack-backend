package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.User
import scala.util.{Try}

object UsersDAO {

  val users = TableQuery[models.database.Users]

  def autoInc = users returning users

  def list: Try[List[User]] = {
    Try {
      DB.withSession{ implicit session =>
        users.list
      }
    }
  }

  def create(user: User): Try[User] = {
    Try {
      DB.withSession { implicit session =>
        autoInc += user
      }
    }
  }

  def findByHandle(handle: String) = {
    Try {
      DB.withSession { implicit session =>
        users.filter(_.handle === handle).firstOption
      }
    }
  }
}

