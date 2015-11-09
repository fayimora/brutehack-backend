package com.brutehack.services

import javax.inject.Inject

import com.brutehack.domain.Problem
import com.github.racc.tscg.TypesafeConfig
import com.twitter.inject.Logging
import scalikejdbc._

/**
 * Created by fayimora on 09/11/2015.
 */
class ProblemsService @Inject()(
  @TypesafeConfig("db.driver") dbDriver: String,
  @TypesafeConfig("db.url") dbUrl: String,
  @TypesafeConfig("db.user") dbUser: String,
  @TypesafeConfig("db.password") dbPassword: String) extends Logging {

  Class.forName(dbDriver)
  ConnectionPool.singleton(dbUrl, dbUser, dbPassword)
  implicit val session = AutoSession

  object ProblemDB extends SQLSyntaxSupport[Problem]{
    def apply(sp: SyntaxProvider[Problem])(rs: WrappedResultSet): Problem =
      apply(sp.resultName)(rs)

    def apply(e: ResultName[Problem])(rs: WrappedResultSet): Problem = {
      new Problem(
        id = rs.string(e.id),
        authorId = rs.string(e.authorId),
        title = rs.string(e.title),
        description = rs.string(e.description)
      )
    }
  }
}