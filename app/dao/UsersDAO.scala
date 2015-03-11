package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.User
import scala.util.{Try}

object UsersDAO {

  val users = TableQuery[models.database.Users]

  def autoInc = users returning users.map(_.id)

  def create(user: User) = {
    Try {
      DB.withSession { implicit session =>
        user.id match {
          case None => autoInc += user
          case Some(x) => users += user
        }
        // users.insert(user)
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

