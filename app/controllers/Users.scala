package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.{User, UserProfile}
import dao.UserDAO
import scala.util.{Success, Failure}
import java.sql.Timestamp
import concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Users extends Controller {

  implicit val timeReads: Reads[Timestamp] = (__ \ "time").read[Long].map{ long => new Timestamp(long) }

  implicit val userProfileWrites: Writes[UserProfile] = (
    (__ \ "lastVisit").write[Timestamp] and
    (__ \ "handle").write[String] and
    (__ \ "firstName").write[String] and
    (__ \ "lastName").write[String] and
    (__ \ "email").write[String] and
    (__ \ "rating").write[Int] and
    (__ \ "location").write[String] and
    (__ \ "shirtSize").write[String] and
    (__ \ "id").writeNullable[Long] and
    (__ \ "createdAt").writeNullable[Timestamp]
  )(unlift(UserProfile.unapply))

  implicit val userReads: Reads[User] = (
    (__ \ "lastVisit").readNullable[Timestamp].map(_.getOrElse(now)) and
    (__ \ "handle").read[String] and
    (__ \ "firstName").read[String] and
    (__ \ "lastName").read[String] and
    (__ \ "email").read[String] and
    (__ \ "password").readNullable[String].map(_.getOrElse("")) and
    (__ \ "rating").readNullable[Int].map(_.getOrElse(0)) and
    (__ \ "location").readNullable[String].map(_.getOrElse("")) and
    (__ \ "shirtSize").readNullable[String].map(_.getOrElse("")) and
    (__ \ "id").readNullable[Long] and
    (__ \ "createdAt").readNullable[Timestamp].map(_.orElse(Option(now))) and
    (__ \ "updatedAt").readNullable[Timestamp].map(_.orElse(Option(now)))
  )(User.apply _)


  def now = new Timestamp(System.currentTimeMillis())

  def index = Action.async {
    UserDAO.list.map{ usersTry =>
      usersTry.map(users =>
          Ok(Json.toJson(users.map(_.profile)))
      ).getOrElse(InternalServerError)
    }
  }

  def show(handle: String) = Action.async {
    UserDAO.findByHandle(handle).map {
      case Success(userOpt) => userOpt match {
        case Some(user) => Ok(Json.obj("user" -> Json.toJson(user.profile)))
        case None => NotFound(Json.obj("error" -> s"$handle was not found"))
      }
      case Failure(err) => InternalServerError
    }
  }

  def create = Action.async(BodyParsers.parse.json) { implicit request =>
    import play.api.Logger
    val json = request.body \ "user"
    json.validate[User].map{ newUser =>
      val (encryptedPass, salt) = oauth2.Crypto.encryptPassword(newUser.password)
      val encryptedUser = newUser.copy(password = encryptedPass)
      UserDAO.create(encryptedUser).map {
        case Success(u) => Ok(Json.toJson(u.profile))
        case Failure(err) => BadRequest(err.getMessage)
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Json")))
  }
}
