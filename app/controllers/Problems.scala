package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Problem

object Problems extends Controller {

  implicit val problemWrites = new Writes[Problem] {
    def writes(p: Problem) = Json.obj(
      "id" -> p.id,
      "createdAt" -> p.createdAt,
      "updatedAt" -> p.updatedAt,
      "author" -> p.author,
      "title" -> p.title,
      "description" -> p.description,
      "hint" -> p.hint,
      "inputs" -> Json.toJson(p.inputs),
      "outputs" -> Json.toJson(p.outputs)
      )
  }

  implicit val problemListWrites = new Writes[List[Problem]] {
    def writes(c: List[Problem]) = Json.obj("problems" -> Json.toJson((c)))
  }

  def show = Action { implicit request =>
    request.queryString.get("ids[]") match {
      case Some(ls) =>
        val problems = Problem.findByIDs(ls.toList.map(_.toLong))
        Ok(Json.toJson(problems))
      case _ => NotFound(Json.obj("error" -> "Please specify ids query params."))
    }
  }
}
