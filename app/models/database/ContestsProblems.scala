package models.database

import utils.MyPostgresDriver.simple._

class ContestsProblems(tag: Tag) extends Table[(Long, Long, Long)](tag, "contests_problems") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def contestId = column[Long]("contest_id")
  def problemId = column[Long]("problem_id")

  def contest = foreignKey("contest_fk", contestId, TableQuery[Contests])(_.id)
  def problem = foreignKey("problem_fk", problemId, TableQuery[Problems])(_.id)

  def * = (id, contestId, problemId)
}
