package com.brutehack.controllers

import javax.inject.Inject

import com.brutehack.domain.Contest
import com.brutehack.domain.http.{PublishContestRequest, PostContestRequest, GetContestRequest}
import com.twitter.bijection.twitter_util.UtilBijections._
import com.brutehack.services.ContestsService
import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by fayimora on 16/10/2015.
 */
class ContestsController @Inject()(contestsService: ContestsService) extends Controller {
  get("/contests") { req: Request =>
    val fut = contestsService.all()
    twitter2ScalaFuture[Seq[Contest]].invert(fut)
  }

  get("/contests/:id") { req: GetContestRequest =>
    val fut = contestsService.findById(req.id)
    twitter2ScalaFuture[Option[Contest]].invert(fut)
  }

  post("/contests") { req: PostContestRequest =>
    response.notImplemented
  }

  patch("/contests/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/contests/:handle") { req: Request =>
    val id = req.getParam("id")
    val fut = contestsService.delete(id)
    twitter2ScalaFuture[Int].invert(fut).map{ i => response.noContent }
  }

  post("/contests/:id/publish") { req: PublishContestRequest =>
    // TODO: Do something to publish the contest. Perhaps notify everyone registered for the contest
    response.notImplemented
  }

}
