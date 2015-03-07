package models.database

import models.ClientGrantType
import utils.MyPostgresDriver.simple._

class ClientGrantTypes(tag: Tag) extends Table[ClientGrantType](tag, "client_grant_types") {
  def clientId = column[String]("client_id")
  def grantTypeId = column[Long]("grant_type_id")
  def * = (clientId, grantTypeId) <> (ClientGrantType.tupled, ClientGrantType.unapply)
  val pk = primaryKey("pk_client_grant_type", (clientId, grantTypeId))
}

