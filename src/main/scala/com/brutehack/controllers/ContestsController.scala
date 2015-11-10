package com.brutehack.controllers

import javax.inject.Inject

import com.brutehack.domain.Contest
import com.brutehack.domain.http.{PublishContestRequest, PostContestRequest, GetContestRequest}
import com.twitter.bijection.twitter_util.UtilBijections._
import com.brutehack.services.ContestsService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by fayimora on 16/10/2015.
 */
class ContestsController @Inject()(contestsService: ContestsService) extends Controller {
  get("/contests") { req: Request =>
    contestsService.all()
  }

  get("/contests/:id") { req: GetContestRequest =>
    contestsService.findById(req.id)
  }

  post("/contests") { req: PostContestRequest =>
    response.notImplemented
  }

  patch("/contests/:id") { req: Request =>
    response.notImplemented
  }

  delete("/contests/:id") { req: Request =>
    val id = req.getParam("id")
    val rowsAffected = contestsService.delete(id)
    if(rowsAffected == 1) response.noContent else response.internalServerError
  }

  post("/contests/:id/publish") { req: PublishContestRequest =>
    // TODO: Do something to publish the contest. Perhaps notify everyone registered for the contest
    response.notImplemented
  }

}
