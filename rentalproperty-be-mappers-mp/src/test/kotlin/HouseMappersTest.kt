import ru.otus.otuskotlin.marketplace.mappers.openapi.setQuery
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseRead
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HouseMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IRequest = RequestHouseRead(
      houseId = "some-id"
    )
    val context = BeContext()

    context.setQuery(request)

    assertEquals("some-id", context.requestHouseId.id)
  }

  @Test
  fun requestCreateMappingTest() {
    val requestHouse: IRequest = RequestHouseCreate(
      createData = HouseCreateDto(
        price = 10_000_000.0,
        material = HouseMaterialDto.BRICK,
        type = HouseTypeDto.SINGLE_HOUSE,
        floors = 2,
        areaPlot = 10.0,
      )
    )
    val context = BeContext()

    context.setQuery(requestHouse)

    assertEquals(10_000_000.0, context.requestHouse.price)
    assertEquals(2, context.requestHouse.floors)
  }
}
