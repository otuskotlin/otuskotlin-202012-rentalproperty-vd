package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

inline class RoleIdModel(val id: String) {
  companion object {
    val NONE = RoleIdModel("")
  }

  fun asString() = id
}
