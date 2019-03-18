package milewski.compositions

object Example extends App {
  val f: Double => String = (d: Double) => d.toString

  val g: String => Int = (s: String) => s.length

  println((g compose f)(5.343))
}
