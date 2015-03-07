package controllers

import play.api.mvc.{Action, Controller}
import oauth2.OAuth2DataHandler
import scalaoauth2.provider.OAuth2Provider

// not sure why I need this yet
import scala.concurrent.ExecutionContext.Implicits.global

object OAuth2Controller extends Controller with OAuth2Provider {
  def accessToken = Action.async { implicit request =>
    issueAccessToken(new OAuth2DataHandler())
  }
}

