package monoids

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

object Example2 extends App {

  def addWithoutMonoid(list: List[Int]): Int =  list.foldLeft(0)(_ + _)

  def addWithMonoid(list: List[Int]): Int = {
    list.foldLeft(Monoid.empty[Int])(_ |+| _)
  }

  val result1 = addWithoutMonoid(List(2,3,5))
  val result2 = addWithMonoid(List(2,3,5))

  assert(result1 == true)
  assert(result2 == true)
}
