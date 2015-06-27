package clf4s

import org.scalameter.api._
import org.scalameter.execution.SeparateJvmsExecutor

import scala.io.Source

object LogEntryBenchmark extends PerformanceTest {

  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = ChartReporter(ChartFactory.NormalHistogram())
  lazy val persistor = Persistor.None


  try {
    val logEntriesIterator = Source.fromFile("src/test/resources/perf-test-sample.log")
    val logEntries = Gen.enumeration("logEntry")(logEntriesIterator.getLines().toList: _*)
    println("Log sample for performance testing loaded, running performance tests... ")
    performance of "LogEntry" in {
      measure method "apply" config (
        exec.benchRuns -> 64,
        exec.minWarmupRuns -> 1000,
        exec.maxWarmupRuns -> 10000,
        exec.warmupCovThreshold -> 1.0
        ) in {
        using(logEntries) in { logEntry =>
          LogEntry(logEntry)
        }
      }
    }

  } catch {
    case e: Throwable =>
      println(s"Performance testing skipped for the following reason:\n\t${e.getClass.getName}: ${e.getMessage}")
  }

}
