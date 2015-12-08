package com.brutehack.controllers

import javax.inject.Inject

import com.brutehack.Crypto
import com.brutehack.domain.http._
import com.brutehack.services.{IdService, UsersService}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
 * Created by fayimora on 16/10/2015.
 */
class UsersController @Inject()(idService: IdService,
                                usersService: UsersService,
                                crypto: Crypto) extends Controller {
  get("/users") { req: Request =>
    usersService.all()
  }

  get("/users/:handle") { req: GetUserRequest =>
    usersService.findById(req.handle)
  }

  post("/users") { postUser: PostUserRequest =>
    debug(s"postUser: $postUser")
    val (encryptedPassword, salt) = crypto.encryptPassword(postUser.password)
    val encryptedUser = postUser.copy(password = encryptedPassword)
    debug(s"encryptedUser: $encryptedUser")

    val userId = idService.getId
    val user = encryptedUser.toDomain(userId)
    debug(s"user: $user")

    val rowsAffected = usersService.save(user)
    if(rowsAffected == 1) {
      val responseUser = UserResponse.fromDomain(user)
      debug(s"responseUser: $responseUser")
      response.created(responseUser).location(responseUser.handle)
    } else response.internalServerError
  }

  post("/users/login") { req: LoginRequest =>
    val userOpt = usersService.findBy("handle")(req.handle)
    userOpt match {
      case Some(u) =>
        val encryptedPass = crypto.encryptPassword(req.password)._1
        if(encryptedPass == u.password) response.ok else response.unauthorized("invalid username/password")
      case None => response.unauthorized
    }

  }

  patch("/users/:handle") { req: Request =>
    response.notImplemented
  }

  delete("/users/:handle") { req: DeleteUserRequest =>
    val rowsAffected = usersService.delete(req.handle)
    if(rowsAffected == 1) response.noContent else response.internalServerError
  }

}
