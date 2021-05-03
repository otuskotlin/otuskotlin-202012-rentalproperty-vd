package schema

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import java.util.*

class FlatDto(id: EntityID<UUID>) : UUIDEntity(id) {
  var houseId by FlatsTable.houseId
  var number by FlatsTable.number
  var area by FlatsTable.area
  var areaLiving by FlatsTable.areaLiving
  var areaKitchen by FlatsTable.areaKitchen
  var rooms by FlatsTable.rooms
  var floor by FlatsTable.floor
  var ceilingHeight by FlatsTable.ceilingHeight
  var bedrooms by FlatsTable.bedrooms
  var beds by FlatsTable.beds
  var bathroom by FlatsTable.bathroom
  var bathroomType by DirectoryDto referencedOn DirectoriesTable.id
  var balcony by FlatsTable.balcony
  var loggia by FlatsTable.loggia
  var repairType by DirectoryDto referencedOn DirectoriesTable.id
  var viewFromWindow by DirectoryDto referencedOn DirectoriesTable.id
  val conveniences by DirectoryDto referrersOn DirectoriesTable.id
  val appliances by DirectoryDto referrersOn DirectoriesTable.id
  var residents by FlatsTable.residents
  var noSmoking by FlatsTable.noSmoking
  var noAnimals by FlatsTable.noAnimals
  var noChildren by FlatsTable.noChildren
  var noParties by FlatsTable.noParties
  var description by FlatsTable.description
  val photos by MediaFileDto referrersOn MediaFilesTable.id

  fun toModel() = FlatModel(
    id = FlatIdModel(id.value.toString()),
    houseId = HouseIdModel(houseId),
    number = number,
    area = area,
    areaLiving = areaLiving,
    areaKitchen = areaKitchen,
    rooms = rooms,
    floor = floor,
    ceilingHeight = ceilingHeight,
    residents = residents,
    bedrooms = bedrooms,
    beds = beds,
    bathroom = bathroom,
    bathroomType = (bathroomType.toModel()) as BathroomTypeModel,
    balcony = balcony,
    loggia = loggia,
    repairType = (repairType.toModel()) as RepairTypeModel,
    viewFromWindow = (viewFromWindow.toModel()) as ViewFromWindowModel,
    conveniences = conveniences.limit(100)
      .map { (it.toModel()) as ConvenienceModel }.toMutableSet(),
    appliances = appliances.limit(100)
      .map { (it.toModel()) as AppliancesModel }.toMutableSet(),
    noSmoking = noSmoking,
    noAnimals = noAnimals,
    noChildren = noChildren,
    noParties = noParties,
    description = description,
    photos = photos.limit(100).map { it.toModel() }.toMutableSet(),
  )

  fun of(model: FlatModel) {
    houseId = model.houseId.id
    number = model.number
    area = model.area
    areaLiving = model.areaLiving
    areaKitchen = model.areaKitchen
    rooms = model.rooms
    floor = model.floor
    ceilingHeight = model.ceilingHeight
    bedrooms = model.bedrooms
    beds = model.beds
    bathroom = model.bathroom
    //bathroomType = DirectoryDto.of(model.bathroomType)
    balcony = model.balcony
    loggia = model.loggia
    //repairType = DirectoryDto.of(model.repairType)
    //viewFromWindow = DirectoryDto.of(model.viewFromWindow)
    //conveniences = DirectoryDto.of(model.conveniences)
    //appliances = DirectoryDto.of(model.appliances)
    residents = model.residents
    noSmoking = model.noSmoking
    noAnimals = model.noAnimals
    noChildren = model.noChildren
    noParties = model.noParties
    description = model.description
    //photos = MediaFileDto.of(model.photos)
  }

  companion object : UUIDEntityClass<FlatDto>(FlatsTable) {
  }
}
