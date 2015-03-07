package models.database

import models.Client
import utils.MyPostgresDriver.simple._

class Clients(tag: Tag) extends Table[Client](tag, "clients") with TimestampedTable {
  def id          = column[Long]("client_id", O.PrimaryKey, O.NotNull)
  def username    = column[String]("username", O.NotNull)
  def secret      = column[String]("client_secret", O.NotNull)
  def description = column[String]("description", O.NotNull)
  def redirectUri = column[String]("redirect_uri", O.NotNull)
  def scope       = column[String]("scope", O.NotNull)

  def * = (id, username, secret, description, redirectUri, scope, createdAt.?, updatedAt.?) <>
    (Client.tupled, Client.unapply _)
}

