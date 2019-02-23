package monoids

import cats.{Monoid, Semigroup}
import cats.implicits._

object Example4 extends App {

  case class Order(price: Double, quantity: Int)

  implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(0.0, 0)

    override def combine(x: Order, y: Order): Order =
      Order(x.price |+| y.price, x.quantity |+| y.quantity)
  }

  def addOrders(list: List[Order])(implicit M: Monoid[Order]): Order = {
    list.foldLeft(M.empty)(M.combine(_, _))
  }

  val orders = List(Order(34, 3), Order(12, 3), Order(33, 12), Order(90, 4))

  val result  = addOrders(orders)
  println(result)

  object Domain {
    type Product = String
    type Price = Double
    type Number = Int

    val Catalog: Map[Product, Price] = Map(
      "cola" -> 2.75,
      "wine" -> 4.00,
      "beer" -> 3.25
    )

    case class Order(items: Map[Product, Number] = Map()) {
      def add(item: (Product, Number)): Order = Order(this.items |+| Map(item))

      // Implement using Fold
      def total(implicit monoid: Monoid[Price]): Double = ???
    }

    case class Person(name: String)

    implicit val personSemigroup = new Semigroup[Person] {
      override def combine(x: Person, y: Person): Person = Person(x.name |+| y.name)
    }
  }

}
