package com.brutehack.controllers

import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller

/**
 * Created by fayimora on 16/10/2015.
 */
class UsersController extends Controller {
  get("/users") { req: Request =>
    response.notImplemented
  }

  get("/users/:handle") { req: Request =>
    response.notImplemented
  }

  post("/users") { req: Request =>
    response.notImplemented
  }

  patch("/users/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/users/:handle") { req: Request =>
    response.notImplemented
  }

}
