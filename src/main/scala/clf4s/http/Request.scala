package clf4s.http

object Request {

  private val regex = "(\\S+) (\\S+)\\s*(\\S*)\\s*".r

  def unapply(request: String): Option[Request] = request match {
    case regex(m, r, p) => Request(m, r, p)
    case _ => None
  }

  def apply(method: String,
            request: String,
            protocol: String): Option[Request] =
    for (method <- Method(method))
      yield Request(method, request, Protocol(protocol))

}

case class Request(method: Method,
                   resource: String,
                   protocol: Option[Protocol])
