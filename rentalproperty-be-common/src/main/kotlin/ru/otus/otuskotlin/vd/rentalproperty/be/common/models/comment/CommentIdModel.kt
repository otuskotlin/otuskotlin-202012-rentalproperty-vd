package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.comment

inline class CommentIdModel(val id: String) {
  companion object {
    val NONE = CommentIdModel("")
  }
}
