package milewski.compositions.ch2

import scala.collection.mutable
import scala.util.Random

object Challenge1 extends App {

  def memoize[A, B](f: A => B): A => B = new mutable.HashMap[A, B]() { self =>
    override def apply(key: A): B = self.synchronized(getOrElseUpdate(key, f(key)))
  }

  val r = new Random()
  val randomMemoized = memoize(r.nextInt)

  for (_ <- 1 to 10) {
    println(randomMemoized(100))
  }

  val smartMemoize = memoize { keyVal: (Int, Int) =>
    val r = new Random(keyVal._1)
    r.nextInt(keyVal._2)
  }

  println(smartMemoize((10, 100)))
  println(smartMemoize((12, 100)))
  println(smartMemoize((10, 100)))


  // task #4.c
  def f(): Boolean = {
    println("Hello!")
    true
  }

  // task #4.d

  def f(x: Int): Int = {
    var y = 0
    y = y + x
    y
  }

  // task #4.d - scala-way

  def f1(x: Int): Int = {
    val y = 0
    y + x
  }

  println(f(5))
  println(f(5))
  println(f(5))

}

// 2nd approach
class Memoize[-A, +B](f: A => B) extends (A => B) {

  private[this] val values = scala.collection.mutable.Map.empty[A, B]

  def apply(v1: A): B =
    values.getOrElseUpdate(v1, f(v1))
}

object Memoize {
  def apply[A, B](f: A => B): Memoize[A, B] = new Memoize(f)
}