package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.User
import scala.concurrent.{Future, ExecutionContext}

object UsersDAO {

  val users = TableQuery[models.database.Users]

  def findByHandle(handle: String): Option[User] = {
    DB.withSession { implicit session =>
      users.filter(_.handle === handle).firstOption
    }
  }
}

