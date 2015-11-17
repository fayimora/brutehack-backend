package com.brutehack.domain.http

import com.brutehack.services.UsersService
import com.twitter.finatra.request.{RequestInject, RouteParam}

/**
 * Created by fayimora on 17/11/2015.
 */
case class PatchUserRequest(@RequestInject usersService: UsersService,
                            @RouteParam handle: String,
                            newHandle: Option[String],
                            email: Option[String],
                            firstName: Option[String],
                            lastName: Option[String],
                            shirtSize: Option[String],
                            location: Option[String])

