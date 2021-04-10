package ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories

object EmptyUserSession : IUserSession<Any> {
  override val fwSession = object {}
}
