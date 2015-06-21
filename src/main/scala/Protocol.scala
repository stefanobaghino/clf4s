package clf4s.http

sealed abstract class Protocol

object Protocol {

  def apply(protocolString: String): Option[Protocol] = protocolString match {
    case "HTTP/1.0" => Some(`HTTP/1.0`)
    case "HTTP/1.1" => Some(`HTTP/1.1`)
    case "HTTP/2.0" => Some(`HTTP/2.0`)
    case _ => None
  }

  def unapply(method: Method): String = method.toString

}

final object `HTTP/1.0` extends Protocol
final object `HTTP/1.1` extends Protocol
final object `HTTP/2.0` extends Protocol

