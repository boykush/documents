package domain

import domain.SampleEffect._
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffPureOps

import java.time.LocalDate

class BillingFactory {
  def create[R: _idgenm: _idgenm](
      billingDate: LocalDate
  ): Eff[R, Billing] = Billing(BillingId("BILLING_ID")).pureEff[R]
}
