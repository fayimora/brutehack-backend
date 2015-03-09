package controllers

import play.api.mvc._
import play.api.libs.json._
import models.User
import dao.UsersDAO
import scala.util.{Try, Success, Failure}

object Users extends Controller {

  implicit val userWrites = new Writes[User] {
    def writes(p: User) = Json.obj(
      "id"        -> p.id,
      "createdAt" -> p.createdAt,
      "updatedAt" -> p.updatedAt,
      "lastVisit" -> p.lastVisit,
      "handle"    -> p.handle,
      "firstName" -> p.firstName,
      "lastName"  -> p.lastName,
      "email"     -> p.email,
      "rating"    -> p.rating,
      "location"  -> p.location,
      "shirtSize" -> p.shirtSize
      )
  }

  def show(handle: String) = Action {
    UsersDAO.findByHandle(handle) match {
      case Success(opt) => opt match {
        case Some(user) => Ok(Json.obj("user" -> Json.toJson(user)))
        case None => NotFound(Json.obj({"error" -> s"$handle was not found"}))
      }
      case Failure(err) =>
        InternalServerError(Json.obj({"error" -> err.getMessage}))
    }
  }
}
