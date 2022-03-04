package domain

import domain.SampleEffect.{_dbio, _myErrorEither}
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffPureOps

class ContractRepository {
  val contract: Contract = Contract(ContractId("CONTRACT_ID"))

  def findById[R: _dbio](contractId: ContractId): Eff[R, Option[Contract]] =
    Option(contract).pureEff[R]

}
