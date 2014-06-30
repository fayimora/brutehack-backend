package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Contest
import models.database.{Contests => ContestsDB}

object Contests extends Controller {

  implicit val contestWrites = new Writes[Contest] {
    def writes(c: Contest) = Json.obj(
      "id" -> c.id,
      "createdAt" -> c.createdAt,
      "updatedAt" -> c.updatedAt,
      "author" -> c.author,
      "title" -> c.title,
      "description" -> c.description,
      "startTime" -> c.startTime,
      "endTime" -> c.endTime,
      "problems" -> Json.toJson(c.problems)
      )
  }

  implicit val contestListWrites = new Writes[List[Contest]] {
    def writes(c: List[Contest]) = Json.obj("contests" -> Json.toJson((c)))
  }

  def index = Action {
    val contests = ContestsDB.all
    Ok(Json.toJson(contests))
  }
}
