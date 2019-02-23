

object WithoutCats extends App {

  val movies1 = Map(
    "Will Smith" -> List("Wild Wild West", "Bad Boys", "Hitch"),
    "Jada Pinkett" -> List("Woo", "Ali", "Gotham")
  )


  val movies2 = Map (
    "Will Smith" -> List("Made in America"),
    "Angelina Jolie" -> List("Foxfire", "The Bone Collector", "Original")
  )

  // Resulting map should contain same information as below:

  // Map(Will Smith -> List(Wild Wild West, Bad Boys, Hitch, Made in America),
  // Angelina Jolie -> List(Foxfire, The Bone Collector, Original Sin),
  // Jada Pinkett -> List(Woo, Ali, Gotham))

  val dZero = Map[String, List[String]]().withDefaultValue(List[String]())
  val res = (movies1.toList ++ movies2.toList).foldLeft(dZero) {
    case (m, (k, v)) => m.updated(k, m(k) ++ v)
  }

  Stream

  println(res)

}
