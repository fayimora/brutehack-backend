package com.brutehack.controllers

import javax.inject.Inject
import com.twitter.bijection.twitter_util.UtilBijections.twitter2ScalaFuture
import com.brutehack.domain.User
import com.brutehack.services.UsersService
import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by fayimora on 16/10/2015.
 */
class UsersController @Inject()(usersService: UsersService) extends Controller {
  get("/users") { req: Request =>
    val fut = usersService.all()
    twitter2ScalaFuture[Seq[User]].invert(fut)
  }

  get("/users/:handle") { req: Request =>
    val handle = req.getParam("handle")
    val fut = usersService.findById(handle)
    twitter2ScalaFuture[Option[User]].invert(fut)
  }

  post("/users") { req: Request =>
    response.notImplemented
  }

  patch("/users/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/users/:handle") { req: Request =>
    val handle = req.getParam("handle")
    val fut = usersService.delete(handle)
  }

}
