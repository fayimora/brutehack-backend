import play.api.GlobalSettings

import models.database._
import play.api.db.DB
import play.api.Application
import play.api.Play.current
import scala.slick.jdbc.meta.MTable
import utils.MyPostgresDriver.simple._

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    val contests = TableQuery[Contests]
    val problems = TableQuery[Problems]
    database withSession { implicit session =>
      val ddl = (contests.ddl ++ problems.ddl)
      val dbs = Map("CONTESTS" -> contests.ddl, "PROBLEMS" -> problems.ddl)
      dbs foreach { db =>
        if(MTable.getTables(db._1).list.nonEmpty)
          db._2.drop
      }
      ddl.create
    }
  }
}
