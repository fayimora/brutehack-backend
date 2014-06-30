package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DB
import play.api.Play.current
import models.Contest
import models.database.Contests
import utils.MyPostgresDriver.simple._

object Contests extends Controller {
  lazy val database = Database.forDataSource(DB.getDataSource())

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
    database withSession { implicit session =>
      val contests = TableQuery[Contests]
      val cs = contests.list//forEach {c => Json.toJson(c)}
      Ok(Json.toJson(cs))
    }
  }
}
