package models

import java.sql.Timestamp

case class AccessToken(accessToken: String,
                       refreshToken: String,
                       clientId: String,
                       userId: Long,
                       scope: String,
                       expiresIn: Long,
                       id: Option[Long],
                       createdAt: Option[Timestamp] = None,
                       updatedAt: Option[Timestamp] = None)

