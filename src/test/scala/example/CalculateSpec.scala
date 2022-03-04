package example

import org.atnos.eff.{ExecutorServices, Fx, TimedFuture, concurrent}
import org.atnos.eff.all.ThrowableEither
import org.atnos.eff.syntax.all._
import org.scalatest.freespec.AnyFreeSpec
import org.atnos.eff.syntax.future._
import org.scalatest.matchers.must

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class CalculateSpec extends AnyFreeSpec with must.Matchers {
  trait SetUp {
    implicit val sc: concurrent.Scheduler =
      ExecutorServices.schedulerFromGlobalExecutionContext
  }
  "#execute" - {
    "option, eitherの順で実行する" in new SetUp {

      type R = Fx.fx3[Option, ThrowableEither, TimedFuture]

      val futureResult: Future[Either[Throwable, Option[Int]]] =
        Calculate
          .execute[R]
          .runOption
          .runEither[Throwable]
          .runAsync

      val result: Either[Throwable, Option[Int]] =
        Await.result(
          futureResult,
          1.second
        )

      result mustBe Right(Some(7))
    }
    "either, optionの順で実行する" in new SetUp {
      type R = Fx.fx3[Option, ThrowableEither, TimedFuture]

      val futureResult: Future[Option[Either[Throwable, Int]]] =
        Calculate
          .execute[R]
          .runEither[Throwable]
          .runOption
          .runAsync

      val result: Option[Either[Throwable, Int]] =
        Await.result(
          futureResult,
          1.second
        )

      result mustBe Some(Right(7))
    }

    "either, optionの順で実行する" in new SetUp {
      type R = Fx.fx3[Option, ThrowableEither, TimedFuture]

      val result: Option[Option[Int]] =
        Calculate
          .execute[R]
          .runOption
          .runPure // None

      result mustBe Some(7)
    }
  }
}
