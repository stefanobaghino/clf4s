package clf4s.http

sealed abstract class Protocol

case object `HTTP/1.0` extends Protocol
case object `HTTP/1.1` extends Protocol
case object `HTTP/2.0` extends Protocol

