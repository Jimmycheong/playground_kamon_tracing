package jimmy.learning.tutorial

import scala.concurrent.{ExecutionContext, Future}

class SleepService {

  def sleepOperation(timeToSleep: Long)(implicit executionContext: ExecutionContext): Future[Unit] = Future {
    Thread.sleep(timeToSleep)
  }

}
