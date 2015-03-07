package models

import models.database.AuthCodes
import java.sql.Timestamp

case class Client(id: Long,
                  username: String,
                  secret: String,
                  description: String,
                  redirectUri: String,
                  scope: String,
                  createdAt: Option[Timestamp] = None,
                  updatedAt: Option[Timestamp] = None)

