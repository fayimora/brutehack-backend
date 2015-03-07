package models.database

import models.AccessToken
import utils.MyPostgresDriver.simple._

class AccessTokens(tag: Tag) extends Table[AccessToken](tag, "access_tokens") with TimestampedTable {
  def id           = column[Long]("id", O.PrimaryKey, O.AutoInc, O.NotNull)
  def accessToken  = column[String]("token", O.NotNull)
  def refreshToken = column[String]("refresh_token", O.NotNull)
  def clientId     = column[String]("client_id", O.NotNull)
  def userId       = column[Long]("user_id")
  def scope        = column[String]("scope", O.NotNull)
  def expiresIn    = column[Long]("expires_in", O.NotNull)

  def * = (accessToken, refreshToken, clientId, userId, scope, expiresIn, id.?, createdAt.?,
    updatedAt.?) <> (AccessToken.tupled, AccessToken.unapply)
}

