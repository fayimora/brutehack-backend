package com.brutehack

import com.twitter.inject.Logging
import org.mindrot.jbcrypt.BCrypt

/**
 * Created by fayimora on 18/10/2015.
 */
class Crypto extends Logging {

  def encryptPassword(password: String): (String, String) = {
    debug("Generating password Salt")
    val salt = BCrypt.gensalt(15)
    debug("Salt generated")
    val hash = BCrypt.hashpw(password, salt)
    debug("Password hashed")
    (hash, salt)
  }

}
