package jimmy.learning.tutorial

import kamon.Kamon
import kamon.context.{Context, Key}
import kamon.zipkin.ZipkinReporter

object Attempt2 extends App {

  Kamon.addReporter(new ZipkinReporter)

  val sleepService = new SleepService()

  val span = Kamon.buildSpan("find-users").start()
  // Do your operation here.
  import scala.concurrent.ExecutionContext.Implicits._

  operationWithContext {
    sleepService.sleepOperation(1)
  }

  span.finish()
  Kamon.stopAllReporters()


  def operationWithContext[T](f: => T) = {

    val key: Key[String] = Key.broadcast[String]("someKey", "someValue")
    val context = Context().withKey(key, "contextValue")
    Kamon.withContext(context)(f)
  }

}
