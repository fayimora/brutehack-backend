package models.database

import models.Problem
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Problems(tag: Tag) extends Table[Problem](tag, "PROBLEMS") with TimestampedTable {
  def id = column[Long]("PROBLEM_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("TEXT"))
  def hint = column[String]("HINT", O.DBType("TEXT"))
  def inputs = column[List[String]]("INPUTS")
  def outputs = column[List[String]]("OUTPUTS")

  def titleIndex = index("PROBLEM_TITLE", title, unique=true)

  def * = (author, title, description, hint, inputs, outputs, id.?, createdAt.?, updatedAt.?) <>
  ((Problem.apply _).tupled, Problem.unapply)
}

