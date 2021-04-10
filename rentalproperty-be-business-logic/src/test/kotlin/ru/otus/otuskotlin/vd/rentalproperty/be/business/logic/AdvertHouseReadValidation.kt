package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house.AdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class AdvertHouseReadValidation {

  @Test
  fun `houseId success non-empty`() {
    val ctx = BeContext(
      requestAdvertHouseId = AdvertIdModel("test-advert-id")
    )

    runBlocking {
      AdvertHouseRead.execute(ctx)
      assertEquals(BeContextStatus.SUCCESS, ctx.status)
      assertEquals(0, ctx.errors.size)
    }
  }

  @Test
  fun `houseId fails empty`() {
    val ctx = BeContext(
      requestAdvertHouseId = AdvertIdModel("")
    )

    runBlocking {
      AdvertHouseRead.execute(ctx)
      assertEquals(BeContextStatus.ERROR, ctx.status)
      assertTrue { ctx.errors.first().message.contains("empty") }
    }
  }
}
