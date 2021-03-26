package ru.otus.otuskotlin.vd.rentalproperty.be.common.models

data class Error(
    override val code: String = "",
    override val group: IError.Group = IError.Group.NONE,
    override val field: String = "",
    override val level: IError.Level = IError.Level.ERROR,
    override val message: String = ""
) : IError
