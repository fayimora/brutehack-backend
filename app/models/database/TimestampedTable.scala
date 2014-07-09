package models.database

import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

trait TimestampedTable {this: Table[_] =>
  def createdAt = column[Timestamp]("created_at", O.Nullable)
  def updatedAt = column[Timestamp]("updated_at", O.Nullable)
}

