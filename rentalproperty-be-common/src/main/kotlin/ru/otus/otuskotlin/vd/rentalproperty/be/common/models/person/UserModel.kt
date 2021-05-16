package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

import java.time.LocalDate

data class UserModel(
  var id: UserIdModel = UserIdModel.NONE,
  var email: Email = Email.NONE,
  var profile: ProfileModel = ProfileModel.NONE,
  var roles: MutableSet<RoleModel> = mutableSetOf()
) {
  companion object {
    val NONE = UserModel()
    val STUB_ADMIN = UserModel(
      UserIdModel("test-user-id-1"),
      email = Email("test-admin@test.test"),
      profile = ProfileModel(
        fname = "fname-admin",
        mname = "mname-admin",
        lname = "lname-admin",
        dob = LocalDate.of(1980, 10, 5),
        phone = Phone.NONE,
      ),
      roles = mutableSetOf(RoleModel.STUB_ADMIN_SUPER)
    )
    val STUB_USER = UserModel(
      UserIdModel("test-user-id-2"),
      email = Email("test-user@test.test"),
      profile = ProfileModel(
        fname = "fname-user",
        mname = "mname-user",
        lname = "lname-user",
        dob = LocalDate.of(1990, 8, 3),
        phone = Phone.NONE,
      ),
      roles = mutableSetOf(RoleModel.STUB_USER)
    )
  }
}