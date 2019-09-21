package applicatives

import cats.implicits._
import cats.data.{NonEmptyList, Validated, ValidatedNel}

/**
  * Collecting all the error messages
  * what if I have to collect all the error messages happens during my computation?
  * In this case Monad is not the right choice. Monad is for sequencing effects and short-circuiting a computation when
  * something went wrong. You need something else which is called Applicative functor. Unlike Monads, Applicative functor
  * encodes working with multiple independent effects.
  * For collecting all the error messages we can use Validated type and Applicative Builder from Cats.
  *
  * Monad does short-circuit computation when something goes wrong meanwhile Applicative functor continues computation
  * and collects all the results.
  *
  */
object CatsStyle extends App {

  trait Error

  case class Vrm(value: String)

  case class Car(vrm: Vrm, price: Long)
  case class CarNotFound(vrm: Vrm) extends Error

  case class CarStore(private val cars: Seq[Car]) {

    def priceDifference(vrm1: Vrm, vrm2: Vrm): ValidatedNel[Error, Long] = {
      val carValidated1 = Validated.fromOption(
        cars.find(_.vrm == vrm1),
        NonEmptyList.of(CarNotFound(vrm1))
      )
      val carValidated2 = Validated.fromOption(
        cars.find(_.vrm == vrm2),
        NonEmptyList.of(CarNotFound(vrm2))
      )

      (carValidated1, carValidated2).mapN(
        (car1: Car, car2: Car) => difference(car1.price, car2.price)
      )
    }

    private def difference(price1: Long, price2: Long) = price1 - price2

  }

  val vrm1 = Vrm("BMW")
  val vrm2 = Vrm("Mercedes")
  val vrm3 = Vrm("Wolksvagen")
  val vrm4 = Vrm("Audi")
  val vrm5 = Vrm("Captive")
  val vrm6 = Vrm("Malibu")

  val car1 = Car(vrm1, 20000)
  val car2 = Car(vrm2, 30000)
  val car3 = Car(vrm3, 40000)
  val car4 = Car(vrm4, 50000)

  val carStore = CarStore(Seq(car1, car2, car3, car4))

  val r1 = carStore.priceDifference(vrm2, vrm3)
  val r2 = carStore.priceDifference(vrm3, vrm1)
  val r3 = carStore.priceDifference(vrm5, vrm6)
  println(r1)
  println(r2)
  println(r3)

}
