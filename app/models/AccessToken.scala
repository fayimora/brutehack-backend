package models

import java.sql.Timestamp

case class AccessToken(accessToken: String,
                       refreshToken: String,
                       clientId: Option[String],
                       redirectUri: Option[String],
                       userId: Long,
                       scope: Option[String],
                       expiresIn: Long,
                       id: Option[Long],
                       createdAt: Option[Timestamp] = None,
                       updatedAt: Option[Timestamp] = None)

