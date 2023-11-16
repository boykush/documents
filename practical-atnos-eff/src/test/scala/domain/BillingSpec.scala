package domain

import domain.SampleEffect.{MyErrorEither, _myErrorEither}
import org.atnos.eff._
import org.atnos.eff.syntax.all.{
  toEffOnePureValueOps,
  toEffPureOps,
  toEitherEffectOps
}
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

class BillingSpec extends AnyFreeSpec {
  "#execute" - {
    implicit class EitherEffectOps[R, A](private val e: Eff[R, A])
        extends AnyVal {
      def runMyEither[E](implicit
          m: Member[MyErrorEither[*], R]
      ): Eff[m.Out, Either[E, A]] = ???
    }

    "ドメインロジックに対するEffの適用" in {
      def calculateBillingPrice[R: _myErrorEither]: Eff[R, Price] =
        Price(100).pureEff[R]

      type R = Fx.fx1[MyErrorEither]

      val result = calculateBillingPrice[R].runMyEither.runPure.value

      result mustBe Right(Price(100))
    }
  }
}
