package models

import java.util.UUID
import models.database.ConfirmationTokens
import java.sql.Timestamp

case class ConfirmationToken(uuid: UUID,
                             email: String,
                             expirationTime: Timestamp,
                             isSignUp: Boolean,
                             id: Option[Long],
                             createdAt: Option[Timestamp],
                             updatedAt: Option[Timestamp])

