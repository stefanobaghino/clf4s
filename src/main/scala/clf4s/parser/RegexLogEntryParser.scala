package clf4s.parser

import scala.util.{Try, Success, Failure}

import java.text.ParseException
import java.util.Locale

import clf4s.LogEntry
import clf4s.http.Request
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class RegexLogEntryParser extends LogEntryParser {

  private val logEntryRegex =
    "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+ \\S+\\s*\\S*\\s*)\" (\\d{3}) (\\S+)".r

  private val datePattern =
    DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.US)

  def parse(logEntry: String): Either[Throwable, LogEntry] = logEntry match {
    case logEntryRegex(h, i, u, d, r, s, b) => parse(h, i, u, d, r, s, b)
    case _ => Left(new ParseException(logEntry, 0))
  }

  private def parse(h: String,
                    i: String,
                    u: String,
                    d: String,
                    r: String,
                    s: String,
                    b: String): Either[Throwable, LogEntry] =
    Try {
      LogEntry(
        host = h,
        identity = optional(i),
        user = optional(u),
        date = DateTime.parse(d, datePattern),
        request = Request.unapply(r).getOrElse(throw new ParseException(r, 0)),
        status = s.toInt,
        bytes = optional(b).map(_.toLong)
      )
    } match {
      case Success(logEntry) => Right(logEntry)
      case Failure(exception) => Left(exception)
    }

}
