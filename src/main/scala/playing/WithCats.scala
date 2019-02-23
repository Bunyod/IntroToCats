
import cats._
import cats.data._
import cats.implicits._

object WithCats extends App {

//  val movies1 = Map("Will Smith" -> List("Wild Wild West", "Bad Boys", "Hitch"),
//    "Jada Pinkett" -> List("Woo", "Ali", "Gotham")
//  )
//
//  val movies2 = Map("Will Smith" -> List("Made in America"),
//    "Angelina Jolie" -> List("Foxfire", "The Bone Collector", "Original Sin")
//  )
//
//  val merged = movies1 |+| movies2
//
//  println(merged)
//
//
//  def mergeMaps(maps: Map[String, List[String]]*): Map[String, List[String]] = {
//    maps.toList.combineAll
//  }
//
//  val mergedMaps =  mergeMaps(movies1, movies2, Map("Bruce willis" -> List("Die Hard")))

//  println(mergedMaps)
  import scala.concurrent.{ExecutionContext, Future}
  import scala.concurrent.ExecutionContext.Implicits.global

  type Response[A] = EitherT[Future, Throwable, A]

  val errorT: List[EitherT[Future, Throwable, Int]] = List(
    EitherT(Future.successful(1.asRight[Throwable])),
    EitherT(Future(new Throwable("a").asLeft[Int])),
    EitherT( Future.successful(21.asRight[Throwable])),
    EitherT(Future(new Throwable("b").asLeft[Int]))
  )

  import CatsEitherTFutureOps._

//  def futuresEitherToFutures(futures: List[Future[Either[Throwable, Int]]]): Future[List[Int]] = {
//    Future.sequence(futures).map(_.collect { case Right(x) => x })
//  }
//
//  val s = Future.traverse(errorT)(e => e.value)
//  s
//  val res = Future.sequence(errorT.map(_.value)).map(_.collect { case Right(x) => x })
//  println(res)



  import cats.data.EitherT
  import cats.implicits._
  import scala.concurrent.{Await, Future}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._


  case class AvailableCategories(categories: Seq[String], fare: Int)


//  val pf = new PartialFunction[EitherT[Future, Throwable, AvailableCategories]] {
//    override def apply(v1: EitherT[Future, Throwable, AvailableCategories]): Future[AvailableCategories] = ???
//    override def isDefinedAt(x: EitherT[Future, Throwable, AvailableCategories]): Boolean = ???
//  }

  def futuresEitherToFutures[A](futures: List[Future[Either[Throwable, A]]]): Future[List[A]] = {
    Future.sequence(futures).map(_.collect { case Right(x) => x })
  }

  val eitherTList: List[EitherT[Future, Throwable, AvailableCategories]] =
    List(
      EitherT.right[Throwable](Future.successful[AvailableCategories](AvailableCategories(Seq("Hellloooo"), 265))),
      EitherT.left[AvailableCategories](Future.successful[Throwable](new Throwable("Ooops...")))
    )

  val jj = eitherTList.map(_.value)

  val result = futuresEitherToFutures(jj)
  println(result)
//  val syncResult = Await.result(
//    result.value,
//    1.second
//  )
//  println(syncResult)


}