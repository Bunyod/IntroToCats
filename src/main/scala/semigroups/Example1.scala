package semigroups

object Example1 extends App with Data {

  def addMoney(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100, (a.cents + b.cents) % 100)
  }

  def creditSalary(balance: Map[String, Money], salaries: Map[String, Money]): Map[String, Money] = {
    balance.foldLeft(salaries) {
      case (acc, (name, money)) =>
        acc + (name -> acc.get(name).fold(money)(addMoney(_, money)))
    }
  }

  def addMarbles(balances: Map[String, Int], winning: Map[String, Int]): Map[String, Int] = {
    balances.foldLeft(winning) {
      case (acc, (name, marble)) =>
        acc + (name -> acc.get(name).fold(marble)(_ + marble))
    }
  }

  println(s"Credit salary into your account: ${creditSalary(balances, salaries)}")
  println(s"Marbles are credit into your game account: ${addMarbles(marbles, won)}")
}
