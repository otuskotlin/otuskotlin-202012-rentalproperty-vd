package ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions

class RepoNotFoundException(id: String) : RuntimeException("Object with ID=$id is not found")
