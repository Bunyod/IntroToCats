package typeclasses

object Example2 extends App {

  case class Cat(name: String, age: Int, color: String)

  trait Printable[T] {
    def format(value: T): String
  }

  object PrintableInstances {
    implicit val catInstance = new Printable[Cat] {
      override def format(value: Cat): String =
        s"${value.name} is a ${value.age} years old ${value.color} cat"
    }
  }

  implicit class PrintableSyntax[T](value: T) {
    def print(implicit printable: Printable[T]) = println(printable.format(value))
  }

  import PrintableInstances._
  Cat("Kitten", 5, "gray").print
}
