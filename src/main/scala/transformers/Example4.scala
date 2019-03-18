package transformers

import scala.concurrent.{ExecutionContext, Future}
import cats._, cats.data._, cats.implicits._

object Example4 extends App {

  implicit val ec = scala.concurrent.ExecutionContext.global
  case class User(id: Long, name: String)

  sealed trait Error
  object Error {
    final case class UserNotFound(userId: Long) extends Error
    final case class ConnectionError(message: String) extends Error
  }

  type Response[T] = EitherT[Future, Error, T]

  object UserRepo {
    def followers(userId: Long): Response[List[User]] =
      userId match {
        case 0L => EitherT.right(Future { List(User(1, "Michael")) })
        case 1L => EitherT.right(Future { List(User(0, "Vito")) })
        case x =>
          println("not found")
          EitherT.left(Future.successful { Error.UserNotFound(x) })
      }
  }

  import UserRepo.followers
  def isFriends4(user1: Long, user2: Long)
                (implicit ec: ExecutionContext): EitherT[Future, Error, Boolean] =
    for {
      a <- followers(user1)
      b <- followers(user2)
    } yield a.exists(_.id == user2) && b.exists(_.id == user1)


  import scala.concurrent.Await
  import scala.concurrent.duration._

  println(Await.result(isFriends4(0, 1).value, 1.second))

  //  println(Await.result(isFriends3(2, 3).value, 1.second))
}