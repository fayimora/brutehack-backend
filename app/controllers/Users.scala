package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.User

object Users extends Controller {

  implicit val userWrites = new Writes[User] {
    def writes(p: User) = Json.obj(
      "id" -> p.id,
      "createdAt" -> p.createdAt,
      "updatedAt" -> p.updatedAt,
      "lastVisit" -> p.lastVisit,
      "handle" -> p.handle,
      "firstName" -> p.firstName,
      "lastName" -> p.lastName,
      "email" -> p.email,
      "rating" -> p.rating,
      "location" -> p.location,
      "shirtSize" -> p.shirtSize
      )
  }

  def show(handle: String) = Action {
    User.findByHandle(handle) match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(Json.obj({"error" -> s"$handle was not found"}))
    }
  }
}
