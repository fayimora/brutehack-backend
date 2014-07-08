package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Users
import java.sql.Timestamp

case class User(lastVisit: Timestamp,
                handle: String,
                firstName: String,
                lastName: String,
                email: String,
                rating: Int,
                location: String,
                shirtSize: String,
                id: Option[Long],
                createdAt: Option[Timestamp],
                updatedAt: Option[Timestamp])


object User {

  lazy val database = Database.forDataSource(DB.getDataSource())
  val users = TableQuery[Users]

  def findByHandle(handle: String): Option[User] = {
    database withTransaction { implicit session =>
      val res = users.filter(_.handle === handle).list
      res match {
        case user::Nil => Some(user)
        case _ => None
      }
    }
  }
}
