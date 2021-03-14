package ru.otus.otuskotlin.vd.rentalproperty.spring.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import ru.otus.otuskotlin.vd.rentalproperty.spring.BaseTest
import ru.otus.otuskotlin.vd.rentalproperty.spring.repository.UserRepository
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
  val entityManager: TestEntityManager,
  val userRepository: UserRepository
) : BaseTest() {

  @Test
  fun `When findByEmail then return User`() {
    val newUser = getTestUser()
    entityManager.persist(newUser)
    entityManager.flush()
    val user = userRepository.findByEmail(newUser.email)
    assertNotNull(user)
    assertEquals(user.email, newUser.email)
  }

}