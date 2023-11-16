package usecase

import com.google.inject.Inject
import domain.SampleEffect._
import domain.{
  Billing,
  BillingFactory,
  BillingId,
  BillingRepository,
  ProviderId
}
import org.atnos.eff.Eff

import java.time.LocalDate

class GetProviderIdAndBillingsUseCase @Inject() (
    billingRepository: BillingRepository
) {
  def execute[R: _dbio](
      args: GetProviderIdAndBillingsUseCaseArgs
  ): Eff[R, GetProviderIdAndBillingsUseCaseResult] =
    for {
      providerIdAndBillings <- billingRepository
        .findByIds[R](args.billingIds) // Eff[R, Seq[Billing]]
        .map(billings =>
          (args.providerId, billings)
        ) // Eff[R, (ProviderId, Seq[Billing])]
    } yield GetProviderIdAndBillingsUseCaseResult(providerIdAndBillings)
}
case class GetProviderIdAndBillingsUseCaseArgs(
    providerId: ProviderId,
    billingIds: Seq[BillingId]
)
case class GetProviderIdAndBillingsUseCaseResult(
    providerIdAndBillings: (ProviderId, Seq[Billing])
)
