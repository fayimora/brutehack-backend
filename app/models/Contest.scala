package models

import java.sql.Timestamp

case class Contest(id: Long, createdAt: Timestamp, updatedAt: Timestamp, name: String)

