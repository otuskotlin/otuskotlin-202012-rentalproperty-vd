package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class RoleModel(
  var id: RoleIdModel = RoleIdModel.NONE,
  val name: String = "",
  var privileges: MutableSet<RolePrivileges> = mutableSetOf()
) {
  companion object {
    val NONE = RoleModel()
    val STUB_ADMIN_SUPER = RoleModel(
      id = RoleIdModel("test-role-id-1"),
      name = Roles.ADMIN_SUPER.name,
      privileges = mutableSetOf(*RolePrivileges.values()),
    )
    val STUB_USER = RoleModel(
      id = RoleIdModel("test-role-id-2"),
      name = Roles.USER.name,
      privileges = mutableSetOf(
        RolePrivileges.CONTENT_READ,
        RolePrivileges.NOTIFICATION_READ,
        RolePrivileges.ROLE_READ,
        RolePrivileges.USER_READ,
        RolePrivileges.USER_UPDATE
      ),
    )
  }

  enum class Roles {
    USER,
    ADMIN_SUPER,
    ADMIN_RP,
    MODERATOR_RP,
    MANAGER_RP,
  }
}
