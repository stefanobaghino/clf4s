package clf4s.performance

object LogEntryBenchmark extends LogEntryPerformanceTest {

  performance of "LogEntry" in {
    measure method "apply" in {
      using(new LogEntryGenerator) in {
        clf4s.LogEntry(_)
      }
    }
  }

}
