package models

import java.sql.Timestamp

case class Contest(title: String,
                   author: String,
                   description: String,
                   startTime: Timestamp,
                   duration: String,
                   id: Option[Long] = None,
                   createdAt: Option[Timestamp] = None,
                   updatedAt: Option[Timestamp] = None)


