package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class PrincipalModel(
  val id: UserIdModel = UserIdModel.NONE,
  val user: UserModel = UserModel.NONE,
  val authorities: Set<GrantedAuthority> = emptySet(),
  val enabled: Boolean = false,
) {
  companion object {
    val NONE = PrincipalModel()
  }
}
