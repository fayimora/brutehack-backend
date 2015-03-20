package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.AuthCode
import scala.util.{Try}
import scala.concurrent.{Future, ExecutionContext}
import java.sql.Timestamp

object AuthCodeDAO {

  val authCodes = TableQuery[models.database.AuthCodes]

  def find(code: String)(implicit ex: ExecutionContext): Future[Option[AuthCode]] = Future {
    DB.withSession { implicit session =>
      authCodes.filter(a => a.authorizationCode === code).firstOption
    }
  }

}

