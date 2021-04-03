package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.HouseRead
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class HouseReadValidation {

  @Test
  fun `houseId success non-empty`() {
    val ctx = BeContext(
      requestHouseId = HouseIdModel("123")
    )

    runBlocking {
      HouseRead.execute(ctx)
      assertEquals(BeContextStatus.SUCCESS, ctx.status)
      assertEquals(0, ctx.errors.size)
    }
  }

  @Test
  fun `houseId fails empty`() {
    val ctx = BeContext(
      requestHouseId = HouseIdModel("")
    )

    runBlocking {
      HouseRead.execute(ctx)
      assertEquals(BeContextStatus.ERROR, ctx.status)
      assertTrue { ctx.errors.first().message.contains("empty") }
    }
  }
}
