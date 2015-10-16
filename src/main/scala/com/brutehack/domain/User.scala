package com.brutehack.domain

/**
 * Created by fayimora on 16/10/2015.
 */
case class User(id: String,
                handle: String,
                firstName: String,
                lastName: String,
                email: String,
                password: String,
                rating: Int,
                location: String,
                shirtSize: String) {

}