package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.common.MediaFileInMemoryDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.directory.DirectoryInMemoryDto

data class FlatInMemoryDto(
  val id: String? = null,
  val houseId: String? = null,
  val number: String? = null,
  val area: Double? = null,
  val areaLiving: Double? = null,
  val areaKitchen: Double? = null,
  val rooms: Int? = null,
  val floor: Int? = null,
  val ceilingHeight: Double? = null,
  val bedrooms: Int? = null,
  val beds: Int? = null,
  val bathrooms: Int? = null,
  val bathroomType: DirectoryInMemoryDto? = null,
  val balcony: Int? = null,
  val loggia: Int? = null,
  val repairType: DirectoryInMemoryDto? = null,
  val viewFromWindow: DirectoryInMemoryDto? = null,
  val conveniences: Set<DirectoryInMemoryDto>? = null,
  val appliances: Set<DirectoryInMemoryDto>? = null,
  val residents: Int? = null,
  val noSmoking: Boolean? = null,
  val noAnimals: Boolean? = null,
  val noChildren: Boolean? = null,
  val noParties: Boolean? = null,
  val description: String? = null,
  val photos: Set<MediaFileInMemoryDto>? = null,
) {
  fun toModel() = FlatModel(
    id = id?.let { FlatIdModel(it) } ?: FlatIdModel.NONE,
    houseId = houseId?.let { HouseIdModel(it) }
      ?: HouseIdModel.NONE,
    number = number ?: "",
    area = area ?: 0.0,
    areaLiving = areaLiving ?: 0.0,
    areaKitchen = areaKitchen ?: 0.0,
    rooms = rooms ?: 0,
    floor = floor ?: 0,
    ceilingHeight = ceilingHeight ?: 0.0,
    residents = residents ?: 0,
    bedrooms = bedrooms ?: 0,
    beds = beds ?: 0,
    bathrooms = bathrooms ?: 0,
    bathroomType = (bathroomType?.toModel() ?: BathroomTypeModel.NONE) as BathroomTypeModel,
    balcony = balcony ?: 0,
    loggia = loggia ?: 0,
    repairType = (repairType?.toModel() ?: RepairTypeModel.NONE) as RepairTypeModel,
    viewFromWindow = (viewFromWindow?.toModel() ?: ViewFromWindowModel.NONE) as ViewFromWindowModel,
    conveniences = conveniences?.map { (it.toModel()) as ConvenienceModel }
      ?.toMutableSet() ?: mutableSetOf(),
    appliances = appliances?.map { (it.toModel()) as AppliancesModel }
      ?.toMutableSet() ?: mutableSetOf(),
    noSmoking = noSmoking ?: false,
    noAnimals = noAnimals ?: false,
    noChildren = noChildren ?: false,
    noParties = noParties ?: false,
    description = description ?: "",
    photos = photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
  )

  companion object {
    fun of(model: FlatModel) = of(model, model.id.id)

    fun of(model: FlatModel, id: String) = FlatInMemoryDto(
      id = id.takeIf { it.isNotBlank() },
      houseId = model.houseId.id.takeIf { it.isNotBlank() },
      number = model.number.takeIf { it.isNotBlank() },
      area = model.area.takeIf { it != 0.0 },
      areaLiving = model.areaLiving.takeIf { it != 0.0 },
      areaKitchen = model.areaKitchen.takeIf { it != 0.0 },
      rooms = model.rooms.takeIf { it != 0 },
      floor = model.floor.takeIf { it != 0 },
      ceilingHeight = model.ceilingHeight.takeIf { it != 0.0 },
      bedrooms = model.bedrooms.takeIf { it != 0 },
      beds = model.beds.takeIf { it != 0 },
      bathrooms = model.bathrooms.takeIf { it != 0 },
      bathroomType = DirectoryInMemoryDto.of(model.bathroomType),
      balcony = model.balcony.takeIf { it != 0 },
      loggia = model.loggia.takeIf { it != 0 },
      repairType = DirectoryInMemoryDto.of(model.repairType),
      viewFromWindow = DirectoryInMemoryDto.of(model.viewFromWindow),
      conveniences = model.conveniences.takeIf { it.isNotEmpty() }
        ?.map { DirectoryInMemoryDto.of(it) }?.toSet(),
      appliances = model.appliances.takeIf { it.isNotEmpty() }
        ?.map { DirectoryInMemoryDto.of(it) }?.toSet(),
      residents = model.residents.takeIf { it != 0 },
      noSmoking = model.noSmoking.takeIf { it },
      noAnimals = model.noAnimals.takeIf { it },
      noChildren = model.noChildren.takeIf { it },
      noParties = model.noParties.takeIf { it },
      description = model.description.takeIf { it.isNotBlank() },
      photos = model.photos.takeIf { it.isNotEmpty() }
        ?.filter { it != MediaFileModel.NONE }
        ?.map { MediaFileInMemoryDto.of(it) }?.toSet()
    )
  }
}
