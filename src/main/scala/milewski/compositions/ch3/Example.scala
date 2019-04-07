package milewski.compositions.ch3

object Example {

  trait Monoid[A] {
    def empty: A
    def combine(a1: A, a2: A): A
  }

  object Monoid {
    implicit def stringMonoid: Monoid[String] = new Monoid[String] {
      def empty: String = ""
      def combine(a1: String, a2: String): String = a1 + a2
    }
  }
}
