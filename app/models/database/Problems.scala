package models.database

import models.Problem
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Problems(tag: Tag) extends Table[Problem](tag, "problems") with TimestampedTable {
  def id = column[Long]("problem_id", O.PrimaryKey, O.AutoInc, O.NotNull)
  def author = column[String]("author", O.NotNull)
  def title = column[String]("title", O.NotNull)
  def description = column[String]("description", O.NotNull, O.DBType("TEXT"))
  def hint = column[String]("hint", O.DBType("TEXT"))
  def inputs = column[List[String]]("inputs")
  def outputs = column[List[String]]("outputs")

  def titleIndex = index("problem_title", title, unique=true)

  def * = (author, title, description, hint, inputs, outputs, id.?, createdAt.?, updatedAt.?) <>
  ((Problem.apply _).tupled, Problem.unapply)
}

