package models

import play.api.db.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.database.AuthCode
import java.sql.Timestamp

case class AuthCode(authorizationCode: String,
                    userGuid: UUID,
                    redirectUri: Option[String],
                    scope: Option[String],
                    clientId: Option[String],
                    expiresIn: Int,
                    createdAt: Option[Timestamp] = None,
                    updatedAt: Option[Timestamp] = None)

