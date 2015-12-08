package com.brutehack.domain.http

import com.brutehack.domain.User

/**
 * Created by fayimora on 16/10/2015.
 */
case class UserResponse(id: String, handle: String, email: String, rating: Int)

object UserResponse {
  def fromDomain(u: User) = UserResponse(u.id, u.handle, u.email, u.rating)
}
