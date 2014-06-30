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

  def index = Action {
    val problems = Problem.all
    Ok(Json.toJson(problems))
  }
}
