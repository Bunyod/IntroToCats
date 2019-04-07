package milewski.compositions.ch4

object Kleisli extends App {

  type Writer[A] = (A, String)

  // def >=>[A, B, C](m1: A => Writer[B], m2: B => Writer[C]): A => Writer[C]


  object kleisli {
    implicit class KleisliOps[A, B](m1: A => Writer[B]) {
      def >=>[C](m2: B => Writer[C]): A => Writer[C] = x => {
        val (y, s1) = m1(x)
        val (z, s2) = m2(y)
        (z, s1 + s2)
      }
    }
  }

}
