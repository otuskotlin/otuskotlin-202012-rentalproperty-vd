package ru.otus.otuskotlin.vd.rentalproperty.ktor.dsl

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.PrivilegeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RoleEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person.User
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class KotlinDslTest {

  @Test
  fun createUserModel() {
    val user: User = user {
      email("test@test.com")

      profile {
        name {
          first = "Иван"
          midle = "Иванович"
          last = "Ивавнов"
        }
        birth {
          date = LocalDate.parse("2000-01-01")
        }
        contacts {
          phone = "+7 999 999 9999"
        }
      }

      roles {
        add(role {
          name {
            RoleEnum.USER.name
          }
          privileges {
            add(PrivilegeEnum.ROLE_READ)
            add(PrivilegeEnum.CONTENT_READ)
            add(PrivilegeEnum.NOTIFICATION_READ)
          }
        })
      }
    }

    assertEquals("test@test.com", user.email.value)
    assertEquals("Иван", user.profile.fname)
    assertEquals("2000-01-01", user.profile.dob.toString())
    assertEquals(RoleEnum.USER.name, user.roles.first().name)
    assertTrue("permission must contain All permissions") {
      user.roles.first().privileges.containsAll(
        listOf(
          PrivilegeEnum.ROLE_READ,
          PrivilegeEnum.CONTENT_READ,
          PrivilegeEnum.NOTIFICATION_READ
        )
      )
    }
  }

  @Test
  fun infixTest() {
    val user = user {
      profile {
        name {
          first = "Ivan"
        }
      }
    }

    val roleUser = role {
      name {
        RoleEnum.USER
      }
      privileges {
        add(PrivilegeEnum.ROLE_READ)
        add(PrivilegeEnum.CONTENT_READ)
        add(PrivilegeEnum.NOTIFICATION_READ)
      }
    }

    user has roleUser

    assertTrue(user.roles.contains(roleUser))
    assertEquals(RoleEnum.USER.name, user.roles.elementAt(0).name)
  }

}
