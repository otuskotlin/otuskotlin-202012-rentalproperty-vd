package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class PrincipalModel(
  val id: UserIdModel = UserIdModel.NONE,
  val username: String = "",
  val fname: String = "",
  val mname: String = "",
  val lname: String = "",
  val authorities: List<GrantedAuthority> = emptyList(),
  val enabled: Boolean = false,
) {
  companion object {
    val NONE = PrincipalModel()
  }
}
