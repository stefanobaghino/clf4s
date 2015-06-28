package clf4s.parser

import clf4s.LogEntry

object LogEntryParser {

  implicit val regexParser: LogEntryParser = new RegexLogEntryParser
  val stagedParser: LogEntryParser = new StagedLogEntryParser

}

trait LogEntryParser {

  def parse(line: String): Either[Throwable, LogEntry]

}
