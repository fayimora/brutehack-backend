package com.brutehack.controllers

import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller

class BrutehackController extends Controller {
  get("/") { request: Request =>
    response.ok("Welcome to the brutehack-service!")
  }
}

