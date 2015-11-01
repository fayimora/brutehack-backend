package com.brutehack.controllers

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class IndexController extends Controller {
  get("/") { request: Request =>
    response.ok("Welcome to the brutehack-service!")
  }
}

