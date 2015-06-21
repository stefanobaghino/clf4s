package clf4s

import clf4s.http._
import org.scalatest.FlatSpec

import scala.io.Source

class LogEntrySpec extends FlatSpec {

  val oneSample = Source.fromFile("src/test/resources/one-sample.log").getLines().next()

  "A single sample" should "be parsed correctly" in {
    val maybeLogEntry = LogEntry(oneSample)
    assert(maybeLogEntry.isRight)
    maybeLogEntry match {
      case Right(logEntry) =>
        assert(logEntry.host === "in24.inetnebr.com")
        assert(logEntry.identity === None)
        assert(logEntry.user === None)
        assert(logEntry.date.getMillis === 807249601000L)
        assert(logEntry.request.method === GET)
        assert(logEntry.request.resource === "/shuttle/missions/sts-68/news/sts-68-mcc-05.txt")
        assert(logEntry.request.protocol === `HTTP/1.0`)
        assert(logEntry.status === 200)
        assert(logEntry.bytes === 1839)
    }
  }

}
