package com.brutehack.controllers

import javax.inject.Inject

import com.brutehack.OAuthDataHandler
import com.twitter.finagle.OAuth2
import com.twitter.finagle.http.Request
import com.twitter.finagle.oauth2.OAuthError
import com.twitter.finatra.http.Controller
import com.twitter.util.Future

/**
 * Created by fayimora on 12/11/2015.
 */
class OAuthController @Inject()(dataHandler: OAuthDataHandler) extends Controller with OAuth2 {
  get("/oauth2/token") {req: Request =>
    issueAccessToken(req, dataHandler) flatMap { token =>
      val jsonResponse = collection.mutable.Map(
        "access_token" -> token.accessToken,
        "token_type" -> token.tokenType
      ) ++ token.expiresIn.map(
        "expires_in" -> _
      ) ++ token.refreshToken.map(
        "refresh_token" -> _
      ) ++ token.scope.map(
        "scope" -> _
      )
      Future.value(response.ok.json(jsonResponse))
    } handle {
      case e:OAuthError =>
        val jsonError = Map(
          "status" -> e.statusCode,
          "error" -> e.errorType,
          "description" -> e.description
        )
        response.status(e.statusCode).json(jsonError)
    }
  }
}
