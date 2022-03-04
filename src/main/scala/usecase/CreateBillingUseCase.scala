package usecase

import com.google.inject.Inject
import domain.SampleEffect._
import domain.{Billing, BillingFactory, BillingRepository}
import org.atnos.eff.Eff

import java.time.LocalDate

class CreateBillingUseCase @Inject() (
    billingFactory: BillingFactory,
    billingRepository: BillingRepository
) {
  def execute[R: _dbio: _myErrorEither: _idgenm](
      args: CreateBillingUseCaseArgs
  ): Eff[R, CreateBillingUseCaseResult] =
    for {
      billing <- billingFactory.create(args.billingDate)
      storedBilling <- billingRepository.store[R](billing)
    } yield CreateBillingUseCaseResult(storedBilling)
}
case class CreateBillingUseCaseArgs(billingDate: LocalDate)
case class CreateBillingUseCaseResult(billing: Billing)
