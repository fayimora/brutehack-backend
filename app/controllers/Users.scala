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
  // implicit val timestampFormat: Format[Timestamp] = (
  //   (__ \ "time").format[Long]
  // )((long: Long) => new Timestamp(long), (ts: Timestamp) => (ts.getTime))
  // implicit object TimestampFormat extends Format[Timestamp] {
  //   def reads = new Timestamp(
  //     (__ \ "time").format[Long]
  //   )((long: Long) => new Timestamp(long), (ts: Timestamp) => (ts.getTime))
  // }

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

  implicit val userReads: Reads[User] = (
    (__ \ "lastVisit").read[Timestamp] and
    (__ \ "handle").read[String] and
    (__ \ "firstName").read[String] and
    (__ \ "lastName").read[String] and
    (__ \ "email").read[String] and
    (__ \ "rating").read[Int] and
    (__ \ "location").read[String] and
    (__ \ "shirtSize").read[String] and
    (__ \ "id").readNullable[Long] and
    (__ \ "createdAt").readNullable[Timestamp] and
    (__ \ "updatedAt").readNullable[Timestamp]
  )(User.apply _)

  implicit val userFormat: Format[User] = Format(userReads, userWrites)


  case class CreateUser(email: String, handle: String, firstName: String, lastName: String)
  implicit def createUserReads: Reads[CreateUser] = (
    (__ \ "email").read[String] and
    (__ \ "handle").read[String] and
    (__ \ "firstName").read[String] and
    (__ \ "lastName").read[String]
  )(CreateUser.apply _)


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
