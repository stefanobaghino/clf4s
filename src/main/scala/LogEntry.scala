package clf4s

import java.text.ParseException
import java.util.Locale

import clf4s.http.Request
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object LogEntry {

  // In the CLF, missing values are denoted by a dash
  private def optional(s: String): Option[String] = s match {
    case "-" => None
    case _ => Some(s)
  }

  private val logEntryRegex =
    "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+ \\S+\\s*\\S*\\s*)\" (\\d{3}) (\\d+)".r

  private val datePattern = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.US)

  def apply(logEntry: String): Either[Throwable, LogEntry] = logEntry match {
    case logEntryRegex(h, i, u, d, r, s, b) => LogEntry(h, i, u, d, r, s, b)
    case _ => Left(new ParseException(logEntry, 0))
  }

  def apply(h: String, i: String, u: String, d: String, r: String, s: String, b: String): Either[Throwable, LogEntry] =
    try {
      Right(LogEntry(
        host = h,
        identity = optional(i),
        user = optional(u),
        date = DateTime.parse(d, datePattern),
        request = Request.unapply(r).getOrElse(throw new ParseException(r, 0)),
        status = s.toInt,
        bytes = b.toLong
      ))
    } catch {
      case e: Throwable => Left(e)
    }

}

case class LogEntry(host: String, identity: Option[String], user: Option[String], date: DateTime, request: Request, status: Int, bytes: Long)
