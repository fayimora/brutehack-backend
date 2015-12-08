package com.brutehack

import com.brutehack.domain.User
import com.brutehack.domain.http.UserResponse
import com.brutehack.services.{IdService, UsersService}
import com.google.inject.testing.fieldbinder.Bind
import com.twitter.finatra.http.test.{EmbeddedHttpServer, HttpTest}
import com.twitter.finagle.http.Status.{BadRequest, Created, Ok}
import com.twitter.inject.Mockito
import com.twitter.inject.server.FeatureTest

/**
  * Created by fayimora on 07/12/2015.
  */
class UsersFeatureTest extends FeatureTest with Mockito with HttpTest {
  override val server = new EmbeddedHttpServer(new ApplicationServer)

  @Bind val usersService = smartMock[UsersService]
  @Bind val idService = smartMock[IdService]
  @Bind val crypt = smartMock[Crypto]

  "User creation" in {
    val u = User("4d2d848c-27e8-4642-9061-8e5f7010edff","fayi","fayi@brutehack.com","encryptedpass",0,None,None,None,None)
    idService.getId returns u.id
    usersService.save(u) returns 1
    usersService.findBy("handle")("fayi") returns Some(u)

    crypt.encryptPassword(u.password) returns (("encryptedpass", "salt"))

    val result = server.httpPost(
      path = "/users",
      postBody =
        """
        {
          "handle": "fayi",
          "email": "fayi@brutehack.com",
          "password": "encryptedpass"
        }
        """,
      andExpect = Created,
      withJsonBody =
        s"""
        {
          "id": "${u.id}",
          "handle": "${u.handle}",
          "email": "${u.email}",
          "rating": 0
        }
        """)

    server.httpGetJson[UserResponse](
      path = result.location.get,
      andExpect = Ok,
      withJsonBody = result.contentString)
  }

  "Bad user request" in {
    server.httpPost(path = "/users",
      postBody =
        """
        {
          "handle": "",
          "email": "fayi@brutehack.com",
          "password": "e"
        }
        """,
      andExpect = BadRequest,
      withJsonBody =
        """
        {
          "errors": [
            "handle: cannot be empty",
            "password: size [1] is not between 8 and 50"
          ]
        }
        """)
  }
}
