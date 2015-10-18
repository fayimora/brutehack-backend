package com.brutehack.domain

import org.joda.time.DateTime

/**
 * Created by fayimora on 16/10/2015.
 */
case class Contest(id: String,
                   title: String,
                   authorId: String,
                   description: String,
                   startTime: Option[DateTime],
                   duration: String,
                   createdAt: Option[DateTime] = None,
                   updatedAt: Option[DateTime] = None)