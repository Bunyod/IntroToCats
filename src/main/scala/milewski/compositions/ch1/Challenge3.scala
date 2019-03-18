package milewski.compositions.ch1

object Challenge3 extends App {


  def add(x: Int)(y: Int): Int = x + y

  assert(Challenge2.composition1(add(1), Challenge1.id[Int])(1) == add(1)(1))

}
