package trampolines

import scalaz.Free.Trampoline
import scalaz.Trampoline

object TrampolineExample extends App {

  /**
    * This code works fine for a small amount of element in the list but as soon as we increase the number of elements it blows up the stack.
    * In this very moment we have to think about using a Trampoline.
    */
//  println(recursiveFunction((1 to 200000).toList)) //This fails

  def recursiveFunction(l:List[Int]): List[Int] = {
    if(l.isEmpty) Nil
    else l.head + 1 :: recursiveFunction(l.tail)
  }


  /**
    *  Trampoline is a particular case of a Free Monad and this leads us to the Free monad implementation.
    */

  println(recursiveFunction1((1 to 200000).toList)
    .map(s => println(s.size))
  )

  def recursiveFunction1(l:List[Int]): Trampoline[List[Int]] = {
    if(l.isEmpty)
      Trampoline.done(Nil)
    else {
      Trampoline.suspend(recursiveFunction1(l.tail)).flatMap { list =>
        Trampoline.done(l.head + 1 :: list)
      }
    }
  }
}
