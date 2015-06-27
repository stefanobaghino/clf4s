package clf4s

import org.scalameter.api._
import org.scalameter.execution.SeparateJvmsExecutor

import scala.io.Source

object LogEntryBenchmark extends PerformanceTest {

  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  try {
    val logEntries = Gen.enumeration("logEntry")(Source.fromFile("src/test/resources/perf-test-sample.log").getLines().toList: _*)
    println("Full log successfully loaded, running performance tests... ")
    performance of "LogEntry" in {
      measure method "apply" in {
        using(logEntries) in { logEntry =>
          LogEntry(logEntry)
        }
      }
    }

  } catch {
    case e: Throwable =>
      println(s"Performance testing skipped for the following reason:\n${e.getClass.getName}: ${e.getMessage}")
  }

}
