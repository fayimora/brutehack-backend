import play.api.GlobalSettings

import play.api.Application
import play.api.mvc.WithFilters
import utils.CORSFilter

object Global extends WithFilters(CORSFilter()) with GlobalSettings {

  override def onStart(app: Application) {
  }
}
