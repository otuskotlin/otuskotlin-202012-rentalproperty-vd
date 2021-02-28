package ru.otus.otuskotlin.vd.rentalproperty.spring.unit

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.otus.otuskotlin.vd.rentalproperty.spring.BaseTest
import ru.otus.otuskotlin.vd.rentalproperty.spring.service.UserService

//@SpringBootTest
//@ExtendWith(SpringExtension::class,MockKExtension::class)
//@ExtendWith(MockKExtension::class)
class UserServiceUnitTest : BaseTest() {

  @MockK
  lateinit var service: UserService

  @BeforeEach
  fun setUp() = MockKAnnotations.init(this)

  @Test
  fun `get all users`() {
    // given
    //val service = mockk<UserService>()
    every { service.getAll() } returns listOf(getTestUser(), getTestUser(), getTestUser())
    // when
    val result = service.getAll()
    // then
    verify { service.getAll() }
    assertTrue(result.isNotEmpty())
    assertEquals(result.size, 3)
  }

  @Test
  fun `get by email`() {
    // given
    val testUser = getTestUser()
    //val service = mockk<UserService>()
    every { service.getByEmail(testUser.email) } returns testUser
    // when
    val result = service.getByEmail(testUser.email)
    // then
    verify { service.getByEmail(testUser.email) }
    assertNotNull(result)
    assertEquals(result.email, testUser.email)
  }

}