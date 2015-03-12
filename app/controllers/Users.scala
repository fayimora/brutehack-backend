package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.User
import dao.UsersDAO
import scala.util.{Try, Success, Failure}
import java.sql.Timestamp

object Users extends Controller {

  implicit val rds: Reads[Timestamp] = (__ \ "time").read[Long].map{ long => new Timestamp(long) }
  implicit val userWrites: Writes[User] = (
    (__ \ "lastVisit").write[Timestamp] and
    (__ \ "handle").write[String] and
    (__ \ "firstName").write[String] and
    (__ \ "lastName").write[String] and
    (__ \ "email").write[String] and
    (__ \ "rating").write[Int] and
    (__ \ "location").write[String] and
    (__ \ "shirtSize").write[String] and
    (__ \ "id").writeNullable[Long] and
    (__ \ "createdAt").writeNullable[Timestamp] and
    (__ \ "updatedAt").writeNullable[Timestamp]
  )(unlift(User.unapply))

  def now = new Timestamp(System.currentTimeMillis())
  implicit val userReads: Reads[User] = (
    (__ \ "lastVisit").readNullable[Timestamp].map(_.getOrElse(now)) and
    (__ \ "handle").read[String] and
    (__ \ "firstName").read[String] and
    (__ \ "lastName").read[String] and
    (__ \ "email").read[String] and
    (__ \ "rating").readNullable[Int].map(_.getOrElse(0)) and
    (__ \ "location").readNullable[String].map(_.getOrElse("")) and
    (__ \ "shirtSize").readNullable[String].map(_.getOrElse("")) and
    (__ \ "id").readNullable[Long] and
    (__ \ "createdAt").readNullable[Timestamp].map(_.orElse(Option(now))) and
    (__ \ "updatedAt").readNullable[Timestamp].map(_.orElse(Option(now)))
  )(User.apply _)

  implicit val userFormat: Format[User] = Format(userReads, userWrites)


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

  def create = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[User].map{ newUser =>
      UsersDAO.create(newUser) match {
        case Success(u) => Ok(Json.toJson(u))
        case Failure(err) => BadRequest(err.getMessage)
      }
    }.getOrElse(BadRequest("error"))
  }
}
