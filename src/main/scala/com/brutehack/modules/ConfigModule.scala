package com.brutehack.modules

import com.github.racc.tscg.TypesafeConfigModule
import com.twitter.inject.{Logging, TwitterModule}
import com.typesafe.config.ConfigFactory

/**
 * Created by fayimora on 13/10/2015.
 */
object ConfigModule extends TwitterModule with Logging {
  override def configure() = {
    info("Loading Config file")
    val config = ConfigFactory.load()
    info(s"App Name from Config: ${config.getString("app.name")}")
    install(TypesafeConfigModule.fromConfig(config))
  }
}

