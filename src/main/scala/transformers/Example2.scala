package transformers

import scala.concurrent.{Future, ExecutionContext}

object Example2 extends App {

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
  def isFriends2(user1: Long, user2: Long)
                (implicit ec: ExecutionContext): Future[Either[Error, Boolean]] =
    for {
      a <- followers(user1)
      b <- followers(user2)
    } yield for {
      x <- a.right
      y <- b.right
    } yield x.exists(_.id == user2) && y.exists(_.id == user1)

  import scala.concurrent.Await
  import scala.concurrent.duration._

  println(Await.result(isFriends2(0, 1), 1.second))

}
