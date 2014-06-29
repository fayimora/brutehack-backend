import play.api.GlobalSettings

import models.database._
import play.api.db.DB
import play.api.Application
import play.api.Play.current

import utils.MyPostgresDriver.simple._

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    val contests = TableQuery[Contests]
    val problems = TableQuery[Problems]
    database withSession { implicit session =>
      val ddl = (contests.ddl ++ problems.ddl)
      ddl.drop
      ddl.create
    }
  }
}
