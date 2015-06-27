package clf4s

import org.scalameter.Parameters
import org.scalameter.api._
import org.scalameter.execution.SeparateJvmsExecutor

import scala.io.Source

package object performance {

  class LogEntryGenerator extends Gen[String] {
    private val warmupDataPath = "src/test/resources/perf-warmup-data.log"
    private val testDataPath = "src/test/resources/perf-test-data.log"
    private def loadFrom(path: String): Iterator[String] = Source.fromFile(path).getLines

    override def warmupset: Iterator[String] = loadFrom(warmupDataPath)
    override def generate(params: Parameters): String = params[String]("logEntry")
    override def dataset: Iterator[Parameters] = loadFrom(testDataPath).map(axis => Parameters("logEntry" -> axis))
  }

  class LogEntryPerformanceTest extends PerformanceTest {
    lazy val executor = SeparateJvmsExecutor(
      new Executor.Warmer.Default,
      Aggregator.min,
      new Measurer.Default)
    lazy val reporter = ChartReporter(ChartFactory.NormalHistogram())
    lazy val persistor = Persistor.None
  }

}
