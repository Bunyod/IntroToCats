import ExceptionHandlerHelper.User
import cats.syntax.option._
import cats.syntax.either._

import scala.concurrent.Future


object ExceptionHandlerHelper {

  implicit class ToFutureSuccessful[T](obj: T) {
    def asFuture: Future[T] = Future.successful(obj)
  }

  val userService = new UserService()
  case class User(userId: String, name: String, age: Int)
  // In UserService we have a method defined as:
  // that we want to mock.

  val intFuture: Future[Int] = ???
  val stringFutures: Future[Int] = ???
  val userFuture: Future[User] = ???

//  userService.getUserById _ expects userId returning user.some.asFuture

  // Mocking return value of service method:

//  userService.ensureUserExists _ expects userId returning user.asRight.asFuture // instead of Future.successful(Right(user))
}

class UserService {
  def getUserById(id: Int): Future[Option[User]] = ???
  def ensureUserExists(id: Int): Future[Either[Exception, User]] = ???

}
