
import cats.data.EitherT

import scala.concurrent.{ExecutionContext, Future}

final class CatsEitherTFutureOps[A, B](val eab: EitherT[Future, A, B]) extends AnyVal {

  import cats.instances.future._

  def rightOrFailure(implicit ec: ExecutionContext): Future[B] =
    eab.getOrElseF(Future.failed(new NoSuchElementException("There is no any elements with your request")))
}

object CatsEitherTFutureOps {
  implicit def ops[A, B](eab: EitherT[Future, A, B]): CatsEitherTFutureOps[A, B] = new CatsEitherTFutureOps[A, B](eab)
}