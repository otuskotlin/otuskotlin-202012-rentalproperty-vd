package ru.otus.otuskotlin.vd.rentalproperty.dsl

import ru.otus.otuskotlin.vd.rentalproperty.model.*

@UserDSL
class UserConfig {
  private var id: UserId = UserId.NONE
  private var email: Email = Email.NONE
  private var profile: Profile = Profile()
  private var roles: MutableSet<Role> = mutableSetOf()

  fun build() = User(
    id = id,
    email = email,
    profile = profile,
    roles = roles
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
    this.profile = profileConf.build()
  }

  fun roles(block: RoleConfig.() -> Unit) {
    val roleConf = RoleConfig().apply(block)
    roles = roleConf.roles.toMutableSet()
  }

}

fun user(block: UserConfig.() -> Unit) = UserConfig().apply(block).build()
