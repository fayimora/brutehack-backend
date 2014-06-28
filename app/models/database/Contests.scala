package models.database

import models.Contest
import scala.slick.driver.PostgresDriver.simple._
import scala.slick.lifted.{ProvenShape}

class Contests(tag: Tag) extends Table[Contest](tag, "CONTESTS") {
  def id = column[Long]("CONTEST_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def name = column[String]("NAME", O.NotNull)

  def * = (id, name) <> (Contest.tupled, Contest.unapply)
}

