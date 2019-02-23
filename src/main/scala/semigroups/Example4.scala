package semigroups

import cats.Semigroup

object Example4 extends App with Data {

  implicit val addMoney = new Semigroup[Money] {
    override def combine(x: Money, y: Money): Money =
      Money((x.dollars + y.dollars) + (x.cents + y.cents) / 100,
        (x.cents + y.cents) % 100
      )
  }

  import cats.instances.int._
  import cats.instances.map._

  def add[A: Semigroup](a: A, b: A)(implicit semigroup: Semigroup[A]): A = semigroup.combine(a, b)

  println(s"Credit salary into your account: ${add(balances, salaries)}")
  println(s"Marbles are credit into your game account: ${add(marbles, won)}")

  println(s"Add omly Money: ${add(Money(76, 22), Money(54, 21))}")

  import cats.syntax.semigroup._
  println(s"Credit salary into your account New syntax: ${balances |+| salaries}")
  println(s"Marbles are credit into your game account New syntax: ${marbles |+| won}")
}
