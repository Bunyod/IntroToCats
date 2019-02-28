package transformers

import scala.concurrent.{ExecutionContext, Future}

object Example3 extends App {

  implicit val ec = scala.concurrent.ExecutionContext.global

  case class User(id: Long, name: String)

  sealed trait Error
  object Error {
    final case class UserNotFound(userId: Long) extends Error
    final case class ConnectionError(message: String) extends Error
  }

  object UserRepo {
    def followers(userId: Long): Future[Either[Error, List[User]]] =  userId match {
      case 0L => Future { Right(List(User(1, "Michael"))) }
      case 1L => Future { Right(List(User(0, "Vito"))) }
      case x =>
        println("not found")
        Future.successful { Left(Error.UserNotFound(x)) }
    }
  }

  import UserRepo.followers
  def isFriends3(user1: Long, user2: Long)
                (implicit ec: ExecutionContext): Future[Either[Error, Boolean]] =
    followers(user1) flatMap {
      case Right(a) =>
        followers(user2) map {
          case Right(b) =>
            Right(a.exists(_.id == user2) && b.exists(_.id == user1))
          case Left(e) =>
            Left(e)
        }
      case Left(e) =>
        Future.successful(Left(e))
    }

  import scala.concurrent.Await
  import scala.concurrent.duration._

  println(Await.result(isFriends3(0, 1), 1.second))
}
