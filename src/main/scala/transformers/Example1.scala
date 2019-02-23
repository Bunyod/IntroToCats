package transformers

object Example1 extends App {

  case class User(id: Long, name: String)

  // In actual code, probably more than 2 errors
  sealed trait Error
  object Error {
    final case class UserNotFound(userId: Long) extends Error
    final case class ConnectionError(message: String) extends Error
  }

  object UserRepo {
    def followers(userId: Long): Either[Error, List[User]] = ???
  }

  import UserRepo.followers
  def isFriends1(user1: Long, user2: Long): Either[Error, Boolean] =
    for {
      a <- followers(user1).right
      b <- followers(user2).right
    } yield a.exists(_.id == user2) && b.exists(_.id == user1)

}
