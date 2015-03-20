package oauth2

import scalaoauth2.provider.{DataHandler, AuthInfo, ClientCredential, AccessToken => AT}
import scala.concurrent.{Future, ExecutionContext}
import models.{User, AccessToken}
import dao.UserDAO
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import java.sql.Timestamp
import java.util.Date
import dao.{AccessTokenDAO, AuthCodeDAO}

class OAuth2DataHandler extends DataHandler[User] {
  private def now = new Timestamp(System.currentTimeMillis())

  def createdAt(token: AccessToken) = new Date(token.createdAt.getOrElse(now).getTime)

  def validateClient(clientCredential: ClientCredential, grantType: String): Future[Boolean] = ???

  def findUser(username: String, password: String): Future[Option[User]] = {
    UserDAO.findByHandle(username).map {
      case Success(user) => user
      case Failure(err) => None
    }
  }

  def createAccessToken(authInfo: AuthInfo[User]): Future[AT] = {
    import oauth2.Crypto
    val expiresIn = 60L * 60L // 1 hour
    val refreshToken = Crypto.generateToken
    val accessToken = Crypto.generateToken
    val userId = authInfo.user.id.get
    val clientId = None
    val redirectUri = None

    val tokenObject = AccessToken(accessToken, refreshToken, clientId, redirectUri, userId, authInfo.scope,
      expiresIn, None, Some(now), Some(now))

    AccessTokenDAO.deleteExistingAndCreate(tokenObject, authInfo.user.id.get)

    Future.successful(
      AT(accessToken, Some(refreshToken), authInfo.scope, Some(expiresIn), now)
    )
  }

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AT]] = {
    AccessTokenDAO.findToken(authInfo.user.id.get).map(optToken =>
      optToken.map{token =>
        AT(token.accessToken, Some(token.refreshToken), token.scope, Some(token.expiresIn), createdAt(token))
      }
    )
  }

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AT] = {
    createAccessToken(authInfo)
  }

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = {
    AuthCodeDAO.find(code).flatMap { optCode =>
      optCode.map { token =>
        UserDAO.findById(token.userId).map {
          case Success(user) =>
            Some(AuthInfo(user.get, token.clientId, token.scope, token.redirectUri))
          case _ =>
            None
        }
      }.getOrElse(Future.successful(None))
    }
  }

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = {
    AccessTokenDAO.findRefreshToken(refreshToken).flatMap{ optToken =>
      optToken.map{ token =>
        UserDAO.findById(token.userId).map {
          case Success(user) =>
            Some(AuthInfo(user.get, token.clientId, token.scope, token.redirectUri))
          case Failure(err) =>
            None
        }
      }.getOrElse(Future.successful(None))
    }
  }

  def findClientUser(clientCredential: ClientCredential, scope: Option[String]): Future[Option[User]] = ???

  def findAccessToken(token: String): Future[Option[AT]] = {
    AccessTokenDAO.findAccessToken(token).map(_.map(token =>
      AT(token.accessToken, Some(token.refreshToken), token.scope, Some(token.expiresIn), createdAt(token)))
    )
  }

  def findAuthInfoByAccessToken(accessToken: AT): Future[Option[AuthInfo[User]]] = ???
}
