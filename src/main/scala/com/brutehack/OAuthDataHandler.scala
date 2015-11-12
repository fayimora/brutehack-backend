package com.brutehack

import java.util.Date
import javax.inject.Singleton

import com.brutehack.domain.User
import com.twitter.finagle.oauth2.{AuthInfo, AccessToken, DataHandler}
import com.twitter.inject.Logging
import com.twitter.util.Future


/**
 * Created by fayimora on 12/11/2015.
 */

@Singleton
class OAuthDataHandler extends DataHandler[User] with Logging {
  val userAccessToken = AccessToken("token1", Some("refreshToken1"), Some("profile photos"), Some(3600), new java.util.Date())
  val id = java.util.UUID.randomUUID().toString
  val user = User(
    id = id,
    handle = "fayimora",
    email = "fayi@fayimora.com",
    password = "thepass",
    rating = 100,
    firstName = None,
    lastName = None,
    location = None,
    shirtSize = None
  )

  def validateClient(clientId: String, clientSecret: String, grantType: String) = {
    debug(s" ===== validateClient($clientId, $clientSecret, $grantType)")
    Future.value(true)
  }

  def findUser(username: String, password: String): Future[Option[User]] = {
    debug(s" ===== findUser($username)")
    Future.value(Some(user))
  }

  def createAccessToken(authInfo: AuthInfo[User]) = {
    debug(" ===== createAccessToken()")
    Future.value(userAccessToken)
  }

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByCode($code)")
    Future.value(Some(AuthInfo(user, "clientid", None, None)))
  }

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByRefreshToken($refreshToken)")
    Future.value(None)
  }

  def findClientUser(clientId: String, clientSecret: String, scope: Option[String]): Future[Option[User]] = {
    debug(s"findClientUser($clientId, $clientSecret, $scope")
    Future.value(None)
  }

  def findAccessToken(token: String): Future[Option[AccessToken]] = {
    debug(s" ===== findAccessToken($token)")
    if(token == "token1") Future.value(Some(userAccessToken))
    else Future.value(None)
  }

  def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByAccessToken($accessToken)")
    if(accessToken.token == "token1") {
      Future.value(Some(AuthInfo(user, "client_id", None, None)))
    } else Future.value(None)
  }

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] = {
    debug(s" ===== getStoredAccessToken($authInfo)")
    Future.value(None)
  }

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = {
    debug(s" ===== refreshAccessToken($authInfo, $refreshToken)")
    Future.value(AccessToken("", Some(""), Some(""), Some(0L), new Date()))
  }
}
