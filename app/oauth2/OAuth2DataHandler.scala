package oauth2

import scalaoauth2.provider.{DataHandler, AuthInfo, ClientCredential, AccessToken => AT}
import scala.concurrent.{Future, ExecutionContext}
import models.{User, AccessToken}
import dao.UserDAO
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import java.sql.Timestamp
import dao.AccessTokenDAO

class OAuth2DataHandler extends DataHandler[User] {
  def validateClient(clientCredential: ClientCredential, grantType: String): Future[Boolean] = ???

  def findUser(username: String, password: String): Future[Option[User]] = Future {
    UserDAO.findByHandle(username).map(user => user).getOrElse(None)
  }

  def createAccessToken(authInfo: AuthInfo[User]): Future[AT] = {
    import oauth2.Crypto
    val expiresIn = 60L * 60L // 1 hour
    val refreshToken = Crypto.generateToken
    val accessToken = Crypto.generateToken
    val now = new Timestamp(System.currentTimeMillis())
    val userId = authInfo.user.id.get
    val clientId = None

    val tokenObject = AccessToken(accessToken, refreshToken, clientId, userId, authInfo.scope,
      expiresIn, None, Some(now), Some(now))

    AccessTokenDAO.deleteExistingAndCreate(tokenObject, authInfo.user.id.get)

    Future.successful(
      AT(accessToken, Some(refreshToken), authInfo.scope, Some(expiresIn), now)
    )
  }

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AT]] = ???

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AT] = ???

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = ???

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???

  def findClientUser(clientCredential: ClientCredential, scope: Option[String]): Future[Option[User]] = ???

  def findAccessToken(token: String): Future[Option[AT]] = ???

  def findAuthInfoByAccessToken(accessToken: AT): Future[Option[AuthInfo[User]]] = ???
}
