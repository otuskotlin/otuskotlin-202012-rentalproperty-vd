package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

abstract class DirectoryModel(
  open val id: DirectoryIdModel = DirectoryIdModel.NONE,
  open val name: String = "",
)
