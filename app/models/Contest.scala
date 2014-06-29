package models

import java.sql.Timestamp

case class Contest(id: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp,
                   title: String,
                   author: String,
                   description: String,
                   startTime: Timestamp,
                   endTime: Timestamp)

