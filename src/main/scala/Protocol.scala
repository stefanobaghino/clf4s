package clf4s.http

sealed abstract class Protocol

object Protocol {

  def apply(protocolString: String): Option[Protocol] = protocolString match {
    case "HTTP/1.0" => Some(`HTTP/1.0`)
    case "HTTP/1.1" => Some(`HTTP/1.1`)
    case "HTTP/2.0" => Some(`HTTP/2.0`)
    case _ => None
  }

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

