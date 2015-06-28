package clf4s

import clf4s.http.Request
import clf4s.parser.LogEntryParser
import org.joda.time.DateTime

object LogEntry {

  def apply(logEntry: String)(implicit parser: LogEntryParser): Either[Throwable, LogEntry] = parser.parse(logEntry)

}

case class LogEntry(host: String,
                    identity: Option[String],
                    user: Option[String],
                    date: DateTime,
                    request: Request,
                    status: Int,
                    bytes: Option[Long])
