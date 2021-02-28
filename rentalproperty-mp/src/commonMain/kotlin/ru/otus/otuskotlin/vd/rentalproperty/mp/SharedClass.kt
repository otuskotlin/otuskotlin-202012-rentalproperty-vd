package ru.otus.otuskotlin.vd.rentalproperty.mp

expect class SharedClass {
  suspend fun request(id: String): String
}
