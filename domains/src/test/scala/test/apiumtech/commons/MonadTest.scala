package test.apiumtech.commons

import scala.concurrent.{Await, Future, ExecutionContext}
import scala.concurrent.duration._

/**
 * Created by christian on 11/08/15.
 */

case object SynchronousExecutionContext extends ExecutionContext {
  override def execute(runnable: Runnable): Unit = runnable.run()
  override def reportFailure(t: Throwable): Unit = throw t
}

trait MonadTest {
  implicit val executionContext: ExecutionContext

    implicit class FutureWithGet[T](a: Future[T]) {
      def get: T = {
        Await.result(a, 1 second)
      }
    }

    def monadic(a: => { def get: Unit }): Unit = a.get
}

trait SynchronousFutureTest {
  implicit val executionContext = SynchronousExecutionContext
}