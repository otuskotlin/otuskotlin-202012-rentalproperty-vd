package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.*

@UserDSL
class UserConfig {
  private var id: UserId = UserId.NONE
  private var email: Email = Email.NONE
  private var profileDsl: ProfileDsl = ProfileDsl()
  private var roleDsls: MutableSet<RoleDsl> = mutableSetOf()

  fun build() = UserDsl(
    id = id,
    email = email,
    profileDsl = profileDsl,
    roleDsls = roleDsls
  )

  private fun id(id: UserId) {
    this.id = id
  }

  fun id(id: String) = id(UserId(id))

  fun email(email: String) {
    this.email = Email(email)
  }

  fun profile(block: ProfileConfig.() -> Unit) {
    val profileConf = ProfileConfig().apply(block)
    this.profileDsl = profileConf.build()
  }

  fun roles(block: RoleConfig.() -> Unit) {
    val roleConf = RoleConfig().apply(block)
    roleDsls = roleConf.roleDsls.toMutableSet()
  }

}

fun user(block: UserConfig.() -> Unit) = UserConfig().apply(block).build()
