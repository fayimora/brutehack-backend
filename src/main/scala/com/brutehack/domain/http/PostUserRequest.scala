package com.brutehack.domain.http

import com.brutehack.domain.User
import com.twitter.finatra.validation.{NotEmpty, Size}

/**
 * Created by fayimora on 18/10/2015.
 */
case class PostUserRequest(@NotEmpty handle: String,
                           @NotEmpty email: String,
                           @Size(min = 8, max = 50) password: String,
                           firstName: Option[String],
                           lastName: Option[String],
                           location: Option[String],
                           shirtSize: Option[String]) {
  def toDomain(id: String) =
    new User(
      id = id,
      handle = handle,
      email = email,
      password = password,
      firstName = firstName,
      lastName = lastName,
      location = location,
      rating = 0,
      shirtSize = shirtSize
    )
}
