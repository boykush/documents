package usecase

import com.google.inject.Inject
import domain.SampleEffect._
import domain.{Billing, BillingFactory, BillingRepository}
import org.atnos.eff.Eff

import java.time.LocalDate

class CreateBillingSampleUseCase @Inject() (
    billingFactory: BillingFactory,
    billingRepository: BillingRepository
) {
  def execute(
      args: CreateBillingSampleUseCaseArgs
  ) = {
    for {
      billing <- billingFactory.create(args.billingDate)
      storedBilling <- billingRepository.store(billing)
    } yield CreateBillingUseCaseResult(storedBilling)
  }
}
case class CreateBillingSampleUseCaseArgs(billingDate: LocalDate)
case class CreateBillingSampleUseCaseResult(billing: Billing)
