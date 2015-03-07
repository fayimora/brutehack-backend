package oauth2

import scalaoauth2.provider.{DataHandler, AuthInfo, ClientCredential}
import scala.concurrent.{Future, ExecutionContext}
import models.{User}

// class OAuth2DataHandler extends DataHandler[User] {
  // def validateClient(clientCredential: ClientCredential, grantType: String): Future[Boolean] = ???
  //
  // def findUser(username: String, password: String): Future[Option[User]] = ???
  //
  // def createAccessToken(authInfo: AuthInfo[User]): Future[AccessToken] = ???
  //
  // def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] = ???
  //
  // def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = ???
  //
  // def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = ???
  //
  // def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???
  //
  // def findClientUser(clientCredential: ClientCredential, scope: Option[String]): Future[Option[User]] = ???
  //
  // def findAccessToken(token: String): Future[Option[AccessToken]] = ???
  //
  // def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = ???
// }