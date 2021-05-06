package ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions

class RepoWrongIdException(id: String) : Throwable("Wrong ID in operation: $id")
