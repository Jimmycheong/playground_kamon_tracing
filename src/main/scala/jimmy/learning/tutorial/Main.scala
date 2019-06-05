package jimmy.learning.tutorial

import com.typesafe.scalalogging.LazyLogging
import kamon.Kamon
import kamon.context.{Context, Key}
import kamon.trace.Span
import kamon.zipkin.ZipkinReporter

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App with LazyLogging {

  // Check application.conf for configuration
  Kamon.addReporter(new ZipkinReporter())

  val sleepService = new SleepService()
  val span = createSpan()
  val UserID = Key.broadcast[String]("userID", "undefined")
//  val SessionID = Key.local[String]("sessionID", "undefined")
  val context = createContext()


  logger.info("Starting tracing operation.")

  Kamon.withSpan(span) {
    logger.info(s"Current span ID: ${Kamon.currentSpan().context().spanID}")
    logger.info(s"Current trace ID: ${Kamon.currentSpan().context().traceID}")
    logger.info(s"Current trace ID: ${Kamon.currentSpan().context().parentID}")
    sleepService.sleepOperation(1L)
//    Kamon.withContext(context) {
//      sleepService.sleepOperation(1L)
//    }
  }

  logger.info("Completed tracing operation.")
  Kamon.stopAllReporters()

  def createSpan(): Span =
    Kamon
      .buildSpan("sleep-operation")
      .withTag("someTag", 4)
      .start()

  def createContext(): Context = Context().withKey(UserID, "1234")

}


