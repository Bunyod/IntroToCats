package playing

object LiftVsRight {


  import cats._
  import cats.data._
  import cats.implicits._
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.util.{Success, Failure}

  type Error = String
  type FutureEither[A] = EitherT[Future, Error, A]

  def getSailMapping(): Future[Int] = {
    Future.successful(5)
  }


  def getPaxConfiguration(): Future[Int] = {
    Future.failed(new Throwable("Bad"))
    // Future.successful(5)
  }

  def divide(a: Double, b: Double): Either[String, Double] =
    Either.cond(b != 0, a / b, "Cannot divide by zero")

  def divideAsync(a: Double, b: Double): FutureEither[Double] = EitherT {
    Future.successful(divide(a, b))
  }

  // liftF is alias for right

  val result = for {
    sail <- EitherT.liftF(getSailMapping())
    pax <- EitherT.liftF(getPaxConfiguration())
    opt <- divideAsync(sail, pax)
  } yield opt


  result.value onComplete {
    case Success(res) => println("Success result " + res)
    case Failure(t) => println("Failed result " + t.getMessage)
  }

}
