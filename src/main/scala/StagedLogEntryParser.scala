package clf4s.parser

import clf4s.LogEntry

class StagedLogEntryParser extends LogEntryParser {
  def parse(line: String): Either[Throwable, LogEntry] = ???
}
