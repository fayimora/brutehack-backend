package models

import java.sql.Timestamp

case class AccessToken(accessToken: String,
                       refreshToken: String,
                       clientId: Option[Long],
                       userId: Long,
                       scope: Option[String],
                       expiresIn: Long,
                       id: Option[Long],
                       createdAt: Option[Timestamp] = None,
                       updatedAt: Option[Timestamp] = None)

