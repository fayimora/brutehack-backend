package com.brutehack.controllers

import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller

/**
 * Created by fayimora on 16/10/2015.
 */
class ContestsController extends Controller {
  get("/contests") { req: Request =>
    response.notImplemented
  }

  get("/contests/:handle") { req: Request =>
    response.notImplemented
  }

  post("/contests") { req: Request =>
    response.notImplemented
  }

  patch("/contests/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/contests/:handle") { req: Request =>
    response.notImplemented
  }

}
