package com.brutehack.domain.http

import com.twitter.finatra.validation.{ValidationResult, MethodValidation, UUID, NotEmpty}

/**
 * Created by fayimora on 19/10/2015.
 */
case class PublishContestRequest(@NotEmpty @UUID id: String) {

  // TODO: Use the dbService to validate the existence of contest with routeparam id
  @MethodValidation
  def validateId = ValidationResult(true, "")
}
