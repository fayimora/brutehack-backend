package com.brutehack.domain.http

import org.joda.time.DateTime

/**
 * Created by fayimora on 18/10/2015.
 */
case class PostContestRequest(title: String,
                              author: String,
                              description: String,
                              startTime: Option[DateTime],
                              duration: Option[String])
