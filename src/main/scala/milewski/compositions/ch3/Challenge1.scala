package milewski.compositions.ch3

class Challenge1 extends App {

  // 1 - Generate a free category from
  // 1.1 - A graph with one node and no edges

  /**
    *  Graph        Free category
    *
    *    O             O<---+
    *                  |    |
    *                  |    |
    *                  +-id-+
    */

  object Graph1 {
    case object Node
  }

  object FreeCategory1 {
    sealed trait Obj
    val id: Obj => Obj = identity[Obj]
  }

  // 1.2 - A graph with one node and one (directed) edge
  /**
    *  Graph             Free category
    *
    *  +-f--+             +-f--+
    *  |    |             |    |
    *  |    |             |    |
    *  +--->O             +--->O<---+
    *                          |    |
    *                          |    |
    *                          +-id-+
    */


  object Graph2 {
    case object Node
    val f: Node.type => Node.type = ???
  }

  object FreeCategory2 {
    sealed trait Obj
    val id: Obj => Obj = identity[Obj]
    val f: Obj => Obj  = ???
  }

  // 1.3 - A graph with two nodes and a single arrow between them
  /**
    *  Graph             Free category
    *
    * A+-f->B         +--->A+-f->B<---+
    *                 |    |     |    |
    *                 |    |     |    |
    *                 +-ida+     +-idb+
    */

  object Graph3 {
    case object Node1
    case object Node2
    val f: Node1.type  => Node2.type = ???


  }

  object FreeCategory3 {
    sealed trait Obj
    case object Obj1 extends Obj
    case object Obj2 extends Obj

    sealed trait A
    sealed trait B
    val id1: A => A = identity[A]
    val id2: B => B = identity[B]
    val f: A => B = ???
  }


  // 1.4 - A graph with a single node and 26 arrows marked with the letters of the alphabet: a, b, c … z.
  /**
    *    Graph                Free category
    *
    *  +-a--+                  +-a--+-id-+
    *  |    |                  |    |    |
    *  |    +                  |    +    |
    *  +--->O<---+             +--->O<---+
    *       +    |                  +    |
    *       |    |                  |    |
    *       +-b--+                  +-b--+
    */

  object Graph4 {
    object Node
    val a: Node.type => Node.type = ???
    val b: Node.type => Node.type = ???
    val c: Node.type => Node.type = ???
    val d: Node.type => Node.type = ???
  }

  object FreeCategory4 {
    sealed trait A
    val id: A => A = identity[A]
    val a: A => A = ???
    val b: A => A = ???
    val c: A => A = ???
    val d: A => A = ???
  }

  // 2 - What kind of order is this
  // 2.1 - A set of sets with the inclusion relation: A is included in B if every element of A is also an element of B.
  /**
    * If A ⊂ B and B ⊂ A, A = B, the order is partial
    * Sets that have no elements in common are not related, the order is not total.
    */
  // 2.2 - C++ types with the following subtyping relation: T1 is a subtype of T2 if a pointer to T1 can be passed
  // to a function that expects a pointer to T2 without triggering a compilation error
  /**
    * If type A is a subtype of type B and type B is a subtype of type A, A = B, the order is partial
    * Not all type have a common type hierarchy, the order is no total
    */

  import milewski.compositions.ch3.Example.Monoid
  implicit def boolAndInstnace: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true
    def combine(b1: Boolean, b2: Boolean): Boolean = b1 && b2
  }

  implicit def boolOrInstnace: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    def combine(b1: Boolean, b2: Boolean): Boolean = b1 || b2
  }


  // 4 - Represent the Bool monoid with the AND operator as a category: List the morphisms and their rules of composition.
  /**
    * +andT+
    * |    |
    * |    +
    * +--->Bool<---+
    * |    +  +    |
    * |    |  |    |
    * +andF+  +-id-+
    *
    * Morphisms: id, andTrue, andFalse
    * Rules of composition:
    * - andTrue.andTrue   = andTrue
    * - andTrue.andFalse  = andFalse
    * - andFalse.andTrue  = andFalse
    * - andFalse.andFalse = andFalse
    * - (id.f) = f
    */

  // 5 - Represent addition modulo 3 as a monoid category
  /**
    * +add1+
    * |    |
    * |    +
    * +--->AddM3<---+
    * |    +   +    |
    * |    |   |    |
    * +add2+   +-id-+
    *
    * Morphisms: id(add0), add1, add2
    * Rules of composition:
    * - add1.add1 = add2
    * - add1.add2 = id
    * - add2.add1 = id
    * - add2.add2 = add1
    */

}
