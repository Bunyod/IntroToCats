package milewski.compositions.ch1

object Challenge2 extends App {

  // Bartosz Milewski Essence of Composition Challenge 2.
  // Implement the composition function in your favorite language.
  // It takes two functions as arguments and returns a function that is their composition.
  /**
    * Composition function.
    *
    * @params f a function from A -> B
    * @params g a function from B -> C
    * @return The composition of the two, g ∘ f
    *
    * Composition properties:
    * - Associativity: h∘(g∘f) = (h∘g)∘f = h∘g∘f
    */
  // Bartosz Milewski Essence of Composition Challenge 2.
  // Implement the composition function in your favorite language.
  // It takes two functions as arguments and returns a function that is their composition.
  /**
    * Composition function.
    *
    * @params f a function from A -> B
    * @params g a function from B -> C
    * @return The composition of the two, g ∘ f
    *
    * Composition properties:
    * - Associativity: h∘(g∘f) = (h∘g)∘f = h∘g∘f
    */
  def composition[A, B, C](g: A => B, f: B => C) = (a: A) => f(g(a))
  def composition1[A, B, C](g: A => B, f: B => C): A => C = g andThen f

}
