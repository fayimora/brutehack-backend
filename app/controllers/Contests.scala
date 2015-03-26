package controllers

import play.api.mvc._
import play.api.libs.json._
import models.{Contest, Problem}
import dao.ContestDAO
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

object Contests extends Controller {

  implicit val contestWrites = new Writes[Contest] {
    def writes(c: Contest) = Json.obj(
      "id"          -> c.id,
      "createdAt"   -> c.createdAt,
      "updatedAt"   -> c.updatedAt,
      "author"      -> c.author,
      "title"       -> c.title,
      "description" -> c.description,
      "startTime"   -> c.startTime,
      "duration"    -> c.duration,
      "problems"    -> Json.toJson(Problem.getProblemIds(c.id.get))
      )
  }

  implicit val contestListWrites = new Writes[List[Contest]] {
    def writes(c: List[Contest]) = Json.obj("contests" -> Json.toJson(c))
  }

  def index = Action.async {
    ContestDAO.list.map{ contestsTry =>
      contestsTry.map{ contests =>
        Ok(Json.toJson(contests))
      }.getOrElse(InternalServerError)
    }
  }

  def show(id: Long) = Action.async {
    ContestDAO.findById(id).map {
      case Success(contestOpt) => contestOpt match {
        case Some(contest) => Ok(Json.obj("contest" -> Json.toJson(contest)))
        case None => NotFound(Json.obj("error" -> s"User with $id was not found"))
      }
      case Failure(err) => InternalServerError
    }

  }
}
