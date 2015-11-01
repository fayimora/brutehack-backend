package com.brutehack

import com.brutehack.controllers.{ContestsController, UsersController, IndexController}
import com.brutehack.modules.ConfigModule
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.filter.{LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.logging.modules.Slf4jBridgeModule

object IndexServerMain extends IndexServer

class IndexServer extends HttpServer {
  override def modules = Seq(Slf4jBridgeModule, ConfigModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[IndexController]
      .add[UsersController]
      .add[ContestsController]
  }
}

