package transformers

import scala.concurrent.{ExecutionContext, Future}

object Example3 extends App {

  case class User(id: Long, name: String)

  sealed trait Error
  object Error {
    final case class UserNotFound(userId: Long) extends Error
    final case class ConnectionError(message: String) extends Error
  }

  object UserRepo {
    def followers(userId: Long): Future[Either[Error, List[User]]] = ???
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

}
