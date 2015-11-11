package com.brutehack.controllers

import javax.inject.Inject

import com.brutehack.Crypto
import com.brutehack.domain.User
import com.brutehack.domain.http.{DeleteUserRequest, GetUserRequest, UserResponse, PostUserRequest}
import com.brutehack.services.UsersService
import com.twitter.bijection.twitter_util.UtilBijections.twitter2ScalaFuture
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by fayimora on 16/10/2015.
 */
class UsersController @Inject()(usersService: UsersService) extends Controller {
  get("/users") { req: Request =>
    usersService.all()
  }

  get("/users/:handle") { req: GetUserRequest =>
    usersService.findById(req.handle)
  }

  post("/users") { postUser: PostUserRequest =>
    debug(s"postUser: $postUser")
    val (encryptedPassword, salt) = Crypto.encryptPassword(postUser.password)
    val encryptedUser = postUser.copy(password = encryptedPassword)
    debug(s"encryptedUser: $encryptedUser")
    val userId = java.util.UUID.randomUUID().toString
    val user = encryptedUser.toDomain(userId)
    debug(s"user: $user")
    val fut = usersService.save(user)
    twitter2ScalaFuture[Int].invert(fut).map{ rowsAffected =>
      if(rowsAffected == 1) {
        val responseUser = UserResponse.fromDomain(user)
        debug(s"responseUser: $responseUser")
        response.created(responseUser).location(responseUser.handle)
      } else response.internalServerError
    }
  }

  patch("/users/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/users/:handle") { req: DeleteUserRequest =>
    val fut = usersService.delete(req.handle)
    twitter2ScalaFuture[Int].invert(fut).map{ rowsAffected =>
      if(rowsAffected == 1) response.noContent else response.internalServerError
    }
  }

}
