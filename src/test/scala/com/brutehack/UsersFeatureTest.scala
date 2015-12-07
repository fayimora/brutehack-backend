package com.brutehack

import com.brutehack.domain.User
import com.brutehack.services.UsersService
import com.google.inject.testing.fieldbinder.Bind
import com.twitter.finatra.http.test.{EmbeddedHttpServer, HttpTest}
import com.twitter.finagle.http.Status.Created
import com.twitter.inject.Mockito
import com.twitter.inject.server.FeatureTest

/**
  * Created by fayimora on 07/12/2015.
  */
class UsersFeatureTest extends FeatureTest with Mockito with HttpTest {
  override val server = new EmbeddedHttpServer(new ApplicationServer)

  val user = User("2345","boss","boss@finatra.com","thepassword",123,None,None,None,None)
  @Bind val usersService = smartMock[UsersService]

  "User creation" in {
    usersService.save(user) returns 1

    server.httpPost(
      path = "/users",
      postBody =
        """
        {
          "handle": "boss",
          "email": "boss@finatra.com",
          "password": "this is thepassword"
        }
        """,
      andExpect = Created)
  }
}
