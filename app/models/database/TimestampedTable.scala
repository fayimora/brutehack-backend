package models.database

import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

trait TimestampedTable {this: Table[_] =>
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
}

