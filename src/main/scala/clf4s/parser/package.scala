package clf4s

package object parser {

  // In CLF, missing values are denoted by a dash
  def optional(s: String): Option[String] = s match {
    case "-" => None
    case _ => Some(s)
  }

}
