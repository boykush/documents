package example

import domain.SampleEffect._myErrorEither
import org.atnos.eff.Eff
import org.atnos.eff.syntax.all.toEffApplicativeOps

class Example {
  def domainModelsToDataModels[R: _myErrorEither](
      domainModels: Seq[String]
  ): Eff[R, List[String]] =
    for {
      dataModels <- {
        domainModels.toList // List[DataModel]
          .traverseA(convertToDataModel[R]) // Eff[R, List[DataModel]]
      }
    } yield dataModels

  def convertToDataModel[R: _myErrorEither](str: String): Eff[R, String] = ???

}
