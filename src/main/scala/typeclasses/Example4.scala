package typeclasses

import cats.Show

object Example4 extends App {

  case class Cat(name: String, age: Int, color: String)

  object ShowInstance {
    implicit val catInstance = new Show[Cat] {
      override def show(value: Cat): String =
        s"${value.name} is a ${value.age} years old ${value.color} cat"

    }
  }

  implicit class ShowSyntax[T](value: T) {
    def show(implicit s: Show[T]): String = s.show(value)
  }

  import ShowInstance._
  println(Cat("Garfield", 5, "yellow").show)

}
