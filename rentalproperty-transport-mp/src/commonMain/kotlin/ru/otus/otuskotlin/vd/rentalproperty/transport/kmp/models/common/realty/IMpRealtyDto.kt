package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpItemPermission

interface IMpRealtyDto : IMpRealtyUpdateDto {
  val permissions: Set<MpItemPermission>?
}
