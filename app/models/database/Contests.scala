package models.database

import models.Contest
import scala.slick.driver.PostgresDriver.simple._
import scala.slick.lifted.{ProvenShape}
import java.sql.Timestamp

class Contests(tag: Tag) extends Table[Contest](tag, "CONTESTS") {
  def id = column[Long]("CONTEST_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def name = column[String]("NAME", O.NotNull)

  def * = (id, createdAt, updatedAt, name) <> (Contest.tupled, Contest.unapply)
}

