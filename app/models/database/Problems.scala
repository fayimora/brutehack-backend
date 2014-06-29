package models.database

import models.Problem
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

class Problems(tag: Tag) extends Table[Problem](tag, "PROBLEMS") {
  def id = column[Long]("PROBLEM_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("TEXT"))
  def hint = column[String]("HINT", O.DBType("TEXT"))
  def inputs = column[List[String]]("INPUTS", O.NotNull, O.DBType("TEXT"))
  def outputs = column[List[String]]("OUTPUTS", O.NotNull, O.DBType("TEXT"))

  def * = (id, createdAt, updatedAt, author, title, description, hint, inputs, outputs) <> (Problem.tupled, Problem.unapply)
}

