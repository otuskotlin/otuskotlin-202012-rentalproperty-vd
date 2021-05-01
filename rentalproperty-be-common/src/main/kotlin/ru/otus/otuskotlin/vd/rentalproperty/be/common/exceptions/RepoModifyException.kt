package ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions

class RepoModifyException(id: String) : Throwable("Cannot modify record with id = $id")
