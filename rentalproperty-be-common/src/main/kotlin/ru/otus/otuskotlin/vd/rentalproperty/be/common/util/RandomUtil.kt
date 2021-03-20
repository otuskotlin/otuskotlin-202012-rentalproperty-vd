package ru.otus.otuskotlin.vd.rentalproperty.be.common.util

class RandomUtil {
  fun getRandomStringAlphanumeric(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
      .map { allowedChars.random() }
      .joinToString("")
  }

  fun getRandomStringAlpha(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
      .map { allowedChars.random() }
      .joinToString("")
  }

  fun getRandomStringNumeric(length: Int): String {
    val allowedChars = ('0'..'9')
    return (1..length)
      .map { allowedChars.random() }
      .joinToString("")
  }
}