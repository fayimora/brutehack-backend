package controllers

import play.api.mvc._
import play.api.libs.json._

object Application extends Controller {

  def options(path: String) = Action { Ok("") }

  def index = Action {
    Redirect(routes.Application.api)
  }

  def api = Action {
    Ok(Json.obj("hello" -> "Welcome to BruteHack!"))
  }

}
