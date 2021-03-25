import ru.otus.otuskotlin.marketplace.mappers.openapi.setQuery
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.RequestHouseRead
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
        material = HouseMaterialDto("id", "BRICK"),
        type = HouseTypeDto("id", "SINGLE_HOUSE"),
        floors = 2,
        areaPlot = 10.0,
      )
    )
    val context = BeContext()

    context.setQuery(requestHouse)

    assertEquals(HouseTypeModel(DirectoryIdModel("id"), "SINGLE_HOUSE"), context.requestHouse.type)
    assertEquals(2, context.requestHouse.floors)
  }
}
