package semigroups

object Example3 extends App with Data {

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  implicit val addMoneyIn = new Addable[Money] {
    override def add(a: Money, b: Money): Money =
      Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
        (a.cents + b.cents) % 100
      )
  }

  implicit val addIntIn = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }


  implicit def addMaps[K, V: Addable] = new Addable[Map[K, V]] {
    override def add(a: Map[K, V], b: Map[K, V]): Map[K, V] = a.foldLeft(b) {
      case (acc, (k, v)) => acc + (k -> acc.get(k).fold(v)(implicitly[Addable[V]].add(_, v)))
    }
  }

  def add[A: Addable](a: A, b: A)(implicit addeable: Addable[A]): A = addeable.add(a, b)

  println(s"Credit salary into your account: ${add(balances, salaries)}")
  println(s"Marbles are credit into your game account: ${add(marbles, won)}")
  println(s"Add omly Money: ${add(Money(76, 22), Money(54, 21))}")

}
