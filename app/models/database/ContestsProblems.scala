package models.database

import utils.MyPostgresDriver.simple._

class ContestsProblems(tag: Tag) extends Table[(Long, Long, Long)](tag, "CONTESTS_PROBLEMS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def contestId = column[Long]("CONTEST_ID")
  def problemId = column[Long]("PROBLEM_ID")

  def contest = foreignKey("CONTEST_FK", contestId, TableQuery[Contests])(_.id)
  def problem = foreignKey("PROBLEM_FK", problemId, TableQuery[Problems])(_.id)

  def * = (id, contestId, problemId)
}
