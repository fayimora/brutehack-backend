import play.api.GlobalSettings

import models._
import models.database._
import play.api.db.DB
import play.api.Application
import play.api.Play.current
import scala.slick.jdbc.meta.MTable
import utils.MyPostgresDriver.simple._
import java.sql.Timestamp

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


      val d = new java.util.Date()
      val ts = new Timestamp(d.getTime)
      // contests += Contest(1, ts, ts, "Contest 101", "Fayi", "This is the description", ts, ts, List(6))
      val contestsInsert = contests ++= Seq(
        Contest(1, ts, ts, "Goodbye 2013", "Fayi", "This is the description", ts, ts, List(1,2,3)),
        Contest(2, ts, ts, "Welcome 2014", "Fayi", "This is the description", ts, ts, List(4,5)),
        Contest(3, ts, ts, "Contest 101", "Fayi", "This is the description", ts, ts, List(6))
      )

    }
  }
}
