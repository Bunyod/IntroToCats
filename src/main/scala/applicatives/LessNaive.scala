package applicatives
import scala.util.{Failure, Success, Try}

object LessNaive extends App {

  case class Vrm(value: String)

  case class Car(vrm: Vrm, price: Long)

  class CarNotFoundException(vrm: Vrm) extends Throwable

  case class CarStore(private val cars: Seq[Car]) {

    def priceDifference(vrm1: Vrm, vrm2: Vrm): Try[Long] = {
      val carOpt1 = cars.find(_.vrm == vrm1)
      if (carOpt1.isDefined) {
        val carOpt2 = cars.find(_.vrm == vrm2)
        if (carOpt2.isDefined) {
          Success(difference(carOpt1.get.price, carOpt2.get.price))
        } else {
          Failure(new CarNotFoundException(vrm2))
        }
      } else {
        Failure(new CarNotFoundException(vrm1))
      }
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

  val r = carStore.priceDifference(vrm5, vrm3).isSuccess
  println(r)

}
