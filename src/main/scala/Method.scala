package clf4s.http

object Method {

  def apply(methodName: String): Option[Method] = nameToMethod.get(methodName)

  def unapply(method: Method): String = method.toString

  private val nameToMethod = Map(
    OPTIONS.toString -> OPTIONS,
    GET.toString -> GET,
    HEAD.toString -> HEAD,
    POST.toString -> POST,
    PUT.toString -> PUT,
    DELETE.toString -> DELETE,
    TRACE.toString -> TRACE,
    CONNECT.toString -> CONNECT
  )

}

sealed abstract class Method {
  override val toString: String = this.getClass.getSimpleName.replace('$', ' ').trim
}

object OPTIONS extends Method
object GET extends Method
object HEAD extends Method
object POST extends Method
object PUT extends Method
object DELETE extends Method
object TRACE extends Method
object CONNECT extends Method
