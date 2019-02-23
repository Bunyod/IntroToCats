package typeclasses

import cats.Show
import cats.implicits._

object Example3 extends App {

  case class Cat(name: String, age: Int, color: String)

  val showInt = Show[Int]
  val showString = Show[String]
//  val showCat = Show[Cat]
  println(showInt.show(1231))
  println(showString.show("Hello"))
//  println(showCat.show(Cat("Kitten", 5, "gray")))


  import cats.syntax.all._

  println("Bunyod".show)
}
