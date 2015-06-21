package clf4s.http

object Request {
  
  private val regex = "(\\S+) (\\S+)\\s*(\\S*)".r
  
  def unapply(request: String): Option[Request] = request match {
    case regex(m, r, p) => Request(m, r, p)
    case _ => None
  }
  
  def apply(method: String, request: String, protocol: String): Option[Request] =
    for (method <- Method.apply(method); protocol <- Protocol.apply(protocol)) yield Request(method, request, protocol)
  
}

case class Request(method: Method, resource: String, protocol: Protocol)
