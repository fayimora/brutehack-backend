package oauth2

import scalaoauth2.provider.{DataHandler, AuthInfo, ClientCredential, AccessToken => AT}
import scala.concurrent.{Future, ExecutionContext}
import models.{User, AccessToken}
import dao.UsersDAO
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

class OAuth2DataHandler extends DataHandler[User] {
  def validateClient(clientCredential: ClientCredential, grantType: String): Future[Boolean] = ???

  def findUser(username: String, password: String): Future[Option[User]] = Future {
    UsersDAO.findByHandle(username) match {
      case Success(user) => user
      case Failure(err) => None
    }
  }

  def createAccessToken(authInfo: AuthInfo[User]): Future[AT] = ???

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AT]] = ???

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AT] = ???

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = ???

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???

  def findClientUser(clientCredential: ClientCredential, scope: Option[String]): Future[Option[User]] = ???

  def findAccessToken(token: String): Future[Option[AT]] = ???

  def findAuthInfoByAccessToken(accessToken: AT): Future[Option[AuthInfo[User]]] = ???
}
