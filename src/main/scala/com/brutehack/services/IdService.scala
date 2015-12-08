package com.brutehack.services

import java.util.UUID.randomUUID
/**
  * Created by fayimora on 08/12/2015.
  */
class IdService {
  def getId: String = randomUUID.toString
}
