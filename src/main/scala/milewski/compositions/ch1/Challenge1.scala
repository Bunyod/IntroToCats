package milewski.compositions.ch1

object Challenge1 extends App {

  // Identity object of a category
  // Bartosz Milewski Essence of Composition Challenge 1.
  /**
    * The Identity function.
    *
    * @params x The input
    * @return the given input
    *
    * Identity properties:
    *  - f ∘ id = f = id ∘ f
    */
  // Identity object of a category
  // Bartosz Milewski Essence of Composition Challenge 1.
  /**
    * The Identity function.
    *
    * @params x The input
    * @return the given input
    *
    * Identity properties:
    *  - f ∘ id = f = id ∘ f
    */
  def id[A](a: A) = a

  println(identity(123))
  println(identity("Scala"))

}
