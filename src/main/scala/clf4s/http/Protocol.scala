package clf4s.http

sealed abstract class Protocol

object Protocol {

  private val nameToProtocol = Map(
    `HTTP/1.0`.toString -> `HTTP/1.0`,
    `HTTP/1.1`.toString -> `HTTP/1.1`,
    `HTTP/2.0`.toString -> `HTTP/2.0`
  )

  def apply(protocolString: String): Option[Protocol] =
    nameToProtocol.get(protocolString)

  def unapply(protocol: Protocol): String = protocol.toString

}

object `HTTP/1.0` extends Protocol {
  override val toString: String = "HTTP/1.0"
}
object `HTTP/1.1` extends Protocol {
  override val toString: String = "HTTP/1.1"
}
object `HTTP/2.0` extends Protocol {
  override val toString: String = "HTTP/2.0"
}
