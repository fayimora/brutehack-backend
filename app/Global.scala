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
  }
}
