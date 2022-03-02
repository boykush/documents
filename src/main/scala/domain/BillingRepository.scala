package domain

import domain.SampleEffect.{_dbio, _myErrorEither}
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffPureOps

class BillingRepository {
  def store[R: _dbio: _myErrorEither](billing: Billing): Eff[R, Billing] =
    Billing(BillingId("BILLING_ID")).pureEff[R]

}
