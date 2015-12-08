package com.brutehack

import com.brutehack.domain.User
import com.brutehack.services.{IdService, UsersService}
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

  @Bind val usersService = smartMock[UsersService]
  @Bind val idService = smartMock[IdService]

  "User creation" in {
    usersService.save(any[User]) returns 1
    idService.getId returns "4d2d848c-27e8-4642-9061-8e5f7010edff"

    server.httpPost(
      path = "/users",
      postBody =
        """
        {
          "handle": "boss",
          "email": "boss@brutehack.com",
          "password": "this is thepassword"
        }
        """,
      andExpect = Created,
      withJsonBody =
        """
        {
          "id": "4d2d848c-27e8-4642-9061-8e5f7010edff",
          "handle": "boss",
          "email": "boss@brutehack.com",
          "rating": 0
        }
        """)
  }
}
