import play.api.GlobalSettings

import models.database._
import play.api.db.DB
import play.api.Application
import play.api.Play.current

import scala.slick.driver.PostgresDriver.simple._

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    val contests = TableQuery[Contests]
    database withSession { implicit session =>
      val ddl = (contests.ddl)
      ddl.create
    }
  }
}
