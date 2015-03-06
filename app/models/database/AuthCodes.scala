package models.database

import models.AuthCode
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class AuthCodes(tag: Tag) extends Table[AuthCode](tag, "auth_codes") with TimestampedTable {
  def authorizationCode = column[String]("authorization_code", O.PrimaryKey)
  def userGuid          = column[UUID]("user_guid")
  def redirectUri       = column[Option[String]]("redirect_uri")
  def scope             = column[Option[String]]("scope")
  def clientId          = column[Option[String]]("client_id")
  def expiresIn         = column[Int]("expires_in")
  def * = (authorizationCode, userGuid, redirectUri, scope, clientId, expiresIn, createdAt.?, updatedAt.?) <> (AuthCode.tupled, AuthCode.unapply)
}

