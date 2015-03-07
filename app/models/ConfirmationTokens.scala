package models.database

import models.ConfirmationToken
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp
import java.util.UUID

class ConfirmationTokens(tag: Tag) extends Table[ConfirmationToken](tag, "confirmation_tokens") with TimestampedTable {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def uuid = column[UUID]("uuid")
  def email = column[String]("email")
  def expirationTime = column[Timestamp]("expiration_time")
  def isSignUp = column[Boolean]("is_sign_up")
  def * = (uuid, email, expirationTime, isSignUp, id.?, createdAt.?, updatedAt.?) <> (ConfirmationToken.tupled, ConfirmationToken.unapply)
}

