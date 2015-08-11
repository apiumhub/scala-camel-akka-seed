package com.apiumtech.br.domains.user

import scala.concurrent.{ExecutionContext, Future}

/**
 * @author kevin 
 * @since 7/16/15.
 */
trait UserService {
  implicit def executionContext: ExecutionContext
  implicit def toDTO(user: Future[User]): Future[UserDTO] = user.map(_.toDTO)

  def user(name: String): Future[UserDTO]
}

case class DefaultUserService(repository: UserRepository)(implicit override val executionContext: ExecutionContext) extends UserService {
  def user(name: String) = repository.findByName(name)
}

object UserService {
  def apply(repository: UserRepository = UserRepository.mock)(implicit executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global): DefaultUserService = {
    DefaultUserService(repository)
  }
}