package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.Users
import java.sql.Timestamp

case class UserResponse(id: Option[Long], handle: String, email: String, rating: Int)

case class UserProfile(lastVisit: Timestamp,
                handle: String,
                firstName: String,
                lastName: String,
                email: String,
                rating: Int,
                location: String,
                shirtSize: String,
                id: Option[Long],
                createdAt: Option[Timestamp])

case class User(lastVisit: Timestamp,
                handle: String,
                firstName: String,
                lastName: String,
                email: String,
                password: String,
                rating: Int,
                location: String,
                shirtSize: String,
                id: Option[Long],
                createdAt: Option[Timestamp],
                updatedAt: Option[Timestamp]) {

  def signupResponse =
    UserResponse(id, handle, email, rating)

  def profile =
    UserProfile(lastVisit, handle, firstName, lastName, email, rating, location, shirtSize, id, createdAt)
}

