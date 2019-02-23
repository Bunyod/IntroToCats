package semigroups

object Example2 extends App with Data {

  def addMoney(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100, (a.cents + b.cents) % 100)
  }

  trait Addeable[T] {
    def add(a: T, b: T): T
  }

  def addMaps[K, V](balances: Map[K, V], newMap: Map[K, V])(implicit addeable: Addeable[V]): Map[K, V] = {
    balances.foldLeft(newMap) {
      case (acc, (k, v)) =>
        acc + (k -> acc.get(k).fold(v)(addeable.add(_, v)))
    }
  }

  implicit val addMoneyIn = new Addeable[Money] {
    override def add(a: Money, b: Money): Money = addMoney(a, b)
  }

  implicit val addIntIn = new Addeable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }


  println(s"Credit salary into your account: ${addMaps(balances, salaries)}")
  println(s"Marbles are credit into your game account: ${addMaps(marbles, won)}")
}
