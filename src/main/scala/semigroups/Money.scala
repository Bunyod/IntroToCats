package semigroups

case class Money(dollars: Int, cents: Int)

trait Data {
  val balance = Money(102, 44)
  val salary = Money(320, 0)

  val balances: Map[String, Money] = Map(
    "James" -> Money(212, 98),
    "Jimmy" -> Money(43, 44)
  )

  val salaries: Map[String, Money] = Map(
    "James" -> Money(500, 98),
    "Jimmy" -> Money(500, 44)
  )

  val marbles: Map[String, Int] = Map(
    "James" -> 5,
    "Jimmy" -> 4
  )

  val won: Map[String, Int] = Map(
    "James" -> 2,
    "Jimmy" -> 1
  )

}