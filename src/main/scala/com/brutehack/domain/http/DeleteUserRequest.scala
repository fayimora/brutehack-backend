package com.brutehack.domain.http

import com.twitter.finatra.validation.NotEmpty

/**
 * Created by fayimora on 18/10/2015.
 */
case class DeleteUserRequest(@NotEmpty handle: String)
