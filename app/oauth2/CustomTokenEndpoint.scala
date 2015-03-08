package oauth2

import scalaoauth2.provider.{Password, TokenEndpoint}

class CustomTokenEndpoint extends TokenEndpoint {
  val passwordNoCred = new Password() {
    override def clientCredentialRequired = false
  }

  override val handlers = Map(
    "password" -> passwordNoCred
  )
}

object CustomTokenEndpoint extends CustomTokenEndpoint

