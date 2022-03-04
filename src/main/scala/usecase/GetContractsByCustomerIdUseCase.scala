package usecase

import com.google.inject.Inject
import domain.SampleEffect._
import domain.{
  Billing,
  BillingFactory,
  BillingId,
  BillingRepository,
  ContractId,
  ContractRepository,
  ProviderId
}
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffPureOps

import java.time.LocalDate

class GetBillingsByContractIdUseCase @Inject() (
    contractRepository: ContractRepository,
    billingRepository: BillingRepository
) {
  def execute[R: _dbio](
      args: GetBillingsByContractIdUseCaseArgs
  ): Eff[R, GetBillingsByContractIdUseCaseResult] =
    for {
      maybeContract <- contractRepository.findById[R](args.contractId)
      billings <- maybeContract match {
        case Some(contract) =>
          billingRepository.findByContractId[R](contract.id)
        case None => Nil.pureEff[R] // Eff[R, Seq[Billing]]
      }
    } yield GetBillingsByContractIdUseCaseResult(billings)
}
case class GetBillingsByContractIdUseCaseArgs(
    contractId: ContractId
)
case class GetBillingsByContractIdUseCaseResult(
    billings: Seq[Billing]
)
