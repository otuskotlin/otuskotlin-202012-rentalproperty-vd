package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.UserDsl
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.PrivilegeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RoleEnum
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class KotlinDslTest {

  @Test
  fun createUserModel() {
    val userDsl: UserDsl = user {
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

    assertEquals("test@test.com", userDsl.email.value)
    assertEquals("Иван", userDsl.profileDsl.fname)
    assertEquals("2000-01-01", userDsl.profileDsl.dob.toString())
    assertEquals(RoleEnum.USER.name, userDsl.roleDsls.first().name)
    assertTrue("permission must contain All permissions") {
      userDsl.roleDsls.first().privileges.containsAll(
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

    assertTrue(user.roleDsls.contains(roleUser))
    assertEquals(RoleEnum.USER.name, user.roleDsls.elementAt(0).name)
  }

}
