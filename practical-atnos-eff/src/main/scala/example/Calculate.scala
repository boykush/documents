package example

import org.atnos.eff.{Eff, EitherEffect, Fx, OptionEffect, TimedFuture}
import org.atnos.eff.all._
import org.atnos.eff.create.{_future, fromFuture}

import scala.concurrent.Future

object Calculate extends App {
  def execute[R: _option: _throwableEither: _future]: Eff[R, Int] =
    for {
      x <- fromOption[R, Int](Option(3))
      y <- fromEither[R, Throwable, Int](Right(4))
      z <- fromFuture[R, Int](Future.successful(5))
    } yield x * y - z
}
