package ru.otus.otuskotlin.marketplace.backend.mappers.kmp.exceptions

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus

class WrongBeContextStatus(status: BeContextStatus) :
    RuntimeException("Generated status ${status.name} must not appear in transport mappers")
