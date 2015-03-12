package dao

import play.api.db.slick.DB
import play.api.Play.current
import utils.MyPostgresDriver.simple._
import models.AccessToken
import scala.util.{Try}
import scala.concurrent.{Future, ExecutionContext}
import java.sql.Timestamp

object AccessTokenDAO {

  val accessTokens = TableQuery[models.database.AccessTokens]

  def deleteExistingAndCreate(accessToken: AccessToken, userId: Long)
    (implicit ec: ExecutionContext): Future[Unit] = Future {
    DB.withSession { implicit session =>
      // these two operations should happen inside a transaction
      accessTokens.filter(a => a.userId === userId).delete
      accessTokens.insert(accessToken)
    }
  }

}


