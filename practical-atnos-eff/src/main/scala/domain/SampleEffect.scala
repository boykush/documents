package domain

import org.atnos.eff.{/=, <=, |=}

object SampleEffect extends SampleEffect

trait SampleEffect {
  trait IdGenM[A]
  type _idgenm[R] = IdGenM |= R

  trait DBIO[A]
  type _dbio[R] = DBIO |= R

  trait MyError
  type MyErrorEither[A] = MyError Either A
  type _myErrorEither[R] = MyErrorEither /= R
  type _myErrorEitherMember[R] = MyErrorEither <= R
}
