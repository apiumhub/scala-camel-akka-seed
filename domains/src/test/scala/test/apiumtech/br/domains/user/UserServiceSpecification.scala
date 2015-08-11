package test.apiumtech.br.domains.user

import com.apiumtech.br.domains.user.{MockUserRepository, UserRepository, DefaultUserService, UserService}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FlatSpec, Matchers}
import test.apiumtech.commons.{SynchronousFutureTest, MonadTest}

import scala.concurrent.{Future, ExecutionContext, Await}
import scala.concurrent.duration._
import scala.util.{Success, Failure, Try}

/**
 * @author kevin 
 * @since 7/16/15.
 */
trait UserServiceSpecification extends FlatSpec with Matchers with MonadTest {
  def service: UserService

  "A UserService" should "find a user by name" in {
    monadic {
      for (
        dto <- service.user("name")
      ) yield dto.name should equal("name")
    }
  }
}

case class DefaultUserServiceSpecification() extends UserServiceSpecification with SynchronousFutureTest {
  def service = UserService(MockUserRepository())
}