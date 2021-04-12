package ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories

interface IUserSession<T> {
  val fwSession: T
}
