package clf4s

import scala.io.Source

object LogEntryTestApp extends App {

  for (line <- Source.fromFile("src/test/resources/one-sample.log").getLines()) {
    LogEntry(line) match {
      case Right(logEntry) => println(logEntry)
      case Left(throwable) => throwable.printStackTrace()
    }
  }

}
