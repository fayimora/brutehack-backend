package com.brutehack.domain

/**
 * Created by fayimora on 16/10/2015.
 */
case class User(id: String,
                handle: String,
                email: String,
                password: String,
                rating: Int,
                firstName: Option[String],
                lastName: Option[String],
                location: Option[String],
                shirtSize: Option[String])