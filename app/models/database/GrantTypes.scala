package models.database

import models.GrantType
import utils.MyPostgresDriver.simple._

class GrantTypes(tag: Tag) extends Table[GrantType](tag, "grant_types") {
  def id = column[Long]("id", O.PrimaryKey)
  def grantType = column[String]("grant_type")
  def * = (grantType, id.?) <> (GrantType.tupled, GrantType.unapply)
}

