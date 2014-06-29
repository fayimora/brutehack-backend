package models.database

import models.Problem
import scala.slick.driver.PostgresDriver.simple._
import java.sql.Timestamp

class Problems(tag: Tag) extends Table[Problem](tag, "PROBLEMS") {
  def id = column[Long]("PROBLEM_ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def createdAt = column[Timestamp]("CREATED_AT", O.NotNull)
  def updatedAt = column[Timestamp]("UPDATED_AT", O.NotNull)
  def author = column[String]("AUTHOR", O.NotNull)
  def title = column[String]("TITLE", O.NotNull)
  def description = column[String]("DESCRIPTION", O.NotNull, O.DBType("CLOB"))
  def hint = column[String]("HINT", O.NotNull, O.DBType("CLOB"))
  def input = column[String]("INPUT", O.NotNull, O.DBType("CLOB"))
  def output = column[String]("OUTPUT", O.NotNull, O.DBType("CLOB"))

  def * = (id, createdAt, updatedAt, author, title, description, hint, input, output) <> (Problem.tupled, Problem.unapply)
}

