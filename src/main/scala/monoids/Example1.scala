package monoids

object Example1 extends App {

  trait Monoid[T] {
    def combine(a: T, b: T): T
    def empty: T
  }

  def associativeLaw[T](x: T, y: T, z: T)(implicit M: Monoid[T]): Boolean = {
    M.combine(x, M.combine(y, z)) == M.combine(M.combine(x, y), z)
  }

  def identityLaw[T](x: T)(implicit M: Monoid[T]): Boolean = {
    (M.combine(x, M.empty) == x) && (M.combine(M.empty, x) == x)
  }

  implicit val intMonoid  = new Monoid[Int] {
    override def combine(a: Int, b: Int): Int = a + b

    override def empty: Int = 0
  }
  val result1 = associativeLaw(2,3,5)
  val result2 = identityLaw(2)

  assert(result1 == true)
  assert(result2 == true)
}
