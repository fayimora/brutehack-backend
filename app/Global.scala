import play.api.GlobalSettings

import models._
import models.database._
import play.api.db.DB
import play.api.Application
import play.api.mvc.WithFilters
import play.api.Play.current
import scala.slick.jdbc.meta.MTable
import utils.MyPostgresDriver.simple._
import utils.CORSFilter
import java.sql.Timestamp
import play.Logger

object Global extends WithFilters(CORSFilter()) with GlobalSettings {

  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    val contests = TableQuery[Contests]
    val problems = TableQuery[Problems]
    val users = TableQuery[Users]

    database withSession { implicit session =>
      val ddl = contests.ddl ++ problems.ddl ++ users.ddl
      val dbs = Map("CONTESTS" -> contests.ddl, "PROBLEMS" -> problems.ddl, "USERS" -> users.ddl)
      dbs foreach { db =>
        if(MTable.getTables(db._1).list.nonEmpty)
          db._2.drop
      }
      ddl.create

      val d = new java.util.Date()
      var ts = new Timestamp(d.getTime)

      val contestsInsert = contests ++= Seq(
        Contest(1, ts, ts, "Goodbye 2013", "Fayi", "This is the description", ts, "240mins", List(1,2,3)),
        Contest(2, ts, ts, "Welcome 2014", "Fayi", "This is the description", ts, "120mins", List(4,5)),
        Contest(3, ts, ts, "Contest 101", "Fayi", "This is the description", ts, "75mins", List(6))
      )
      Logger.info(s"Inserted $contestsInsert contests")

      ts = new Timestamp(d.getTime)
      val problemsInsert = problems ++= Seq(
        Problem(1, ts, ts, "Fayimora", "This is the title 1", "This is the description 1", "This is the hint", List("I1", "I2"), List("O1", "O2")),
        Problem(2, ts, ts, "Fayimora", "This is the title 2", "This is the description 2", "This is the hint", List("I1", "I2"), List("O1", "O2")),
        Problem(3, ts, ts, "Fayimora", "This is the title 3", "This is the description 3", "This is the hint", List("I1", "I2"), List("O1", "O2")),
        Problem(4, ts, ts, "Fayimora", "This is the title 4", "This is the description 4", "This is the hint", List("I1", "I2"), List("O1", "O2")),
        Problem(5, ts, ts, "Fayimora", "This is the title 5", "This is the description 5", "This is the hint", List("I1", "I2"), List("O1", "O2")),
        Problem(6, ts, ts, "Fayimora", "This is the title 6", "This is the description 6", "This is the hint", List("I1", "I2"), List("O1", "O2"))
      )
      Logger.info(s"Inserted $problemsInsert problems")

      ts = new Timestamp(d.getTime)
      val usersInsert = users ++= Seq(
        User(1, ts, ts, ts, "fayimora", "Fayimora", "Femi-Balogun", "fayi@fayimora.com", 3345, "London, UK", "L"),
        User(2, ts, ts, ts, "wene", "Wenebunha", "Etitinwo", "wene@wenebunha.com", 2833, "Bradford, UK", "L"),
        User(3, ts, ts, ts, "aqib", "Aqib", "Mushtaq", "aqib@wasemans.com", 3211, "London, UK", "M")
      )
      Logger.info(s"Inserted $usersInsert users")
    }
  }
}
