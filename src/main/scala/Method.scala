package clf4s.http

object Method {

  def apply(methodName: String): Option[Method] = methodName match {
    case m if m == OPTIONS.toString => Some(OPTIONS)
    case m if m == GET.toString => Some(GET)
    case m if m == HEAD.toString => Some(HEAD)
    case m if m == POST.toString => Some(POST)
    case m if m == PUT.toString => Some(PUT)
    case m if m == DELETE.toString => Some(DELETE)
    case m if m == TRACE.toString => Some(TRACE)
    case m if m == CONNECT.toString => Some(CONNECT)
    case _ => None
  }

  def unapply(method: Method): String = method.toString

}

sealed abstract class Method {
  override val toString: String = this.getClass.getSimpleName.replace('$', ' ').trim
}

final object OPTIONS extends Method
final object GET extends Method
final object HEAD extends Method
final object POST extends Method
final object PUT extends Method
final object DELETE extends Method
final object TRACE extends Method
final object CONNECT extends Method
