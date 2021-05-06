package ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions

class RepoIndexException(index: String = "") : RuntimeException("Objects not found by index: $index")
