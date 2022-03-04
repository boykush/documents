package domain

import domain.SampleEffect.{_dbio, _myErrorEither}
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffPureOps

class BillingRepository {
  val billing: Billing = Billing(BillingId("BILLING_ID"))

  def store[R: _dbio: _myErrorEither](billing: Billing): Eff[R, Billing] =
    billing.pureEff[R]

  def findByIds[R: _dbio](billingIds: Seq[BillingId]): Eff[R, Seq[Billing]] =
    Seq(billing).pureEff[R]

  def findByContractId[R: _dbio](contractId: ContractId): Eff[R, Seq[Billing]] =
    Seq(billing).pureEff[R]

}
