package com.brutehack.domain.http

import com.twitter.finatra.validation.{UUID, NotEmpty}

/**
 * Created by fayimora on 18/10/2015.
 */
case class GetContestRequest(@NotEmpty @UUID id: String)
