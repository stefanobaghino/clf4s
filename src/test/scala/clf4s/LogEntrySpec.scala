package clf4s

import clf4s.http._
import org.scalatest.FlatSpec

class LogEntrySpec extends FlatSpec {

  implicit class RichThrowable(throwable: Throwable) {
    lazy val shortStackTrace: String =
      s"${throwable.getClass.getName}: ${throwable.getMessage}\n\t${throwable.getStackTrace.take(10).mkString("\n\t")}"
  }

  val simpleSample =
    "in24.inetnebr.com - - [01/Aug/1995:00:00:01 -0400] \"GET /shuttle/missions/sts-68/news/sts-68-mcc-05.txt HTTP/1.0\" 200 1839"

  val cachedReplySample =
    "gw1.att.com - - [01/Aug/1995:00:03:53 -0400] \"GET /shuttle/missions/sts-73/news HTTP/1.0\" 302 -"

  val noProtocolSample =
    "pipe1.nyc.pipeline.com - - [01/Aug/1995:00:12:37 -0400] \"GET /history/apollo/apollo-13/apollo-13-patch-small.gif\" 200 12859"

  val trailingBlanksSample =
    "ix-sac6-20.ix.netcom.com - - [08/Aug/1995:14:43:39 -0400] \"GET / HTTP/1.0 \" 200 7131"

  "A simple sample" should "be parsed correctly" in {
    val maybeLogEntry = LogEntry(simpleSample)
    maybeLogEntry match {
      case Right(logEntry) =>
        assert(logEntry.host === "in24.inetnebr.com")
        assert(logEntry.identity === None)
        assert(logEntry.user === None)
        assert(logEntry.date.getMillis === 807249601000L)
        assert(logEntry.request.method === GET)
        assert(
          logEntry.request.resource === "/shuttle/missions/sts-68/news/sts-68-mcc-05.txt")
        assert(logEntry.request.protocol === Some(`HTTP/1.0`))
        assert(logEntry.status === 200)
        assert(logEntry.bytes === Some(1839))
      case Left(throwable) =>
        assert(false, throwable.shortStackTrace)
    }
  }

  "A cached page reply (3xx)" should "be parsed correctly" in {
    val maybeLogEntry = LogEntry(cachedReplySample)
    maybeLogEntry match {
      case Right(logEntry) =>
        assert(logEntry.host === "gw1.att.com")
        assert(logEntry.identity === None)
        assert(logEntry.user === None)
        assert(logEntry.date.getMillis === 807249833000L)
        assert(logEntry.request.method === GET)
        assert(logEntry.request.resource === "/shuttle/missions/sts-73/news")
        assert(logEntry.request.protocol === Some(`HTTP/1.0`))
        assert(logEntry.status === 302)
        assert(logEntry.bytes === None)
      case Left(throwable) =>
        assert(false, throwable.shortStackTrace)
    }
  }

  "A line without protocol" should "be parsed correctly" in {
    val maybeLogEntry = LogEntry(noProtocolSample)
    maybeLogEntry match {
      case Right(logEntry) =>
        assert(logEntry.host === "pipe1.nyc.pipeline.com")
        assert(logEntry.identity === None)
        assert(logEntry.user === None)
        assert(logEntry.date.getMillis === 807250357000L)
        assert(logEntry.request.method === GET)
        assert(
          logEntry.request.resource === "/history/apollo/apollo-13/apollo-13-patch-small.gif")
        assert(logEntry.request.protocol === None)
        assert(logEntry.status === 200)
        assert(logEntry.bytes === Some(12859))
      case Left(throwable) =>
        assert(false, throwable.shortStackTrace)
    }
  }

  "A line with trailing blanks" should "be parsed correctly" in {
    val maybeLogEntry = LogEntry(trailingBlanksSample)
    maybeLogEntry match {
      case Right(logEntry) =>
        assert(logEntry.host === "ix-sac6-20.ix.netcom.com")
        assert(logEntry.identity === None)
        assert(logEntry.user === None)
        assert(logEntry.date.getMillis === 807907419000L)
        assert(logEntry.request.method === GET)
        assert(logEntry.request.resource === "/")
        assert(logEntry.request.protocol === Some(`HTTP/1.0`))
        assert(logEntry.status === 200)
        assert(logEntry.bytes === Some(7131))
      case Left(throwable) =>
        assert(false, throwable.shortStackTrace)
    }
  }

}
