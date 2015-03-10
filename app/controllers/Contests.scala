package controllers

import play.api.mvc._
import play.api.libs.json._
import models.{Contest, Problem}

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

  def index = Action {
    val contests = Contest.all
    Ok(Json.toJson(contests))
  }

  def show(id: Long) = Action {
    import controllers.Problems.{problemWrites}
    Contest.findByID(id) match {
      case Some(contest) =>
        // TODO: There has to be a better way to handle this.
        val cprobs = Problem.getProblemIds(contest.id.get).map(id => Json.toJson(Problem.findByID(id).get))
        Ok(Json.obj("contest" -> Json.toJson(contest), "problems" -> Json.toJson(cprobs)))
      case _ =>
        NotFound(Json.obj("error" -> "No such contest exists"))
    }
  }
}
