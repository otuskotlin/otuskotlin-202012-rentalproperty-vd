package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.DirectoryCassandraDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.MediaFileCassandraDto
import java.util.*

@Entity
data class FlatByIdCassandraDto(
  @PartitionKey
  @CqlName(ID)
  val id: String? = null,
  @CqlName(HOUSE_ID)
  val houseId: String? = null,
  @CqlName(NUMBER)
  val number: String? = null,
  @CqlName(AREA)
  val area: Double? = null,
  @CqlName(AREA_LIVING)
  val areaLiving: Double? = null,
  @CqlName(AREA_KITCHEN)
  val areaKitchen: Double? = null,
  @CqlName(ROOMS)
  val rooms: Int? = null,
  @CqlName(FLOOR)
  val floor: Int? = null,
  @CqlName(CEILING_HEIGHT)
  val ceilingHeight: Double? = null,
  @CqlName(BEDROOMS)
  val bedrooms: Int? = null,
  @CqlName(BEDS)
  val beds: Int? = null,
  @CqlName(BATHROOMS)
  val bathrooms: Int? = null,
  @CqlName(BATHROOM_TYPE)
  val bathroomType: DirectoryCassandraDto? = null,
  @CqlName(BALCONY)
  val balcony: Int? = null,
  @CqlName(LOGGIA)
  val loggia: Int? = null,
  @CqlName(REPAIR_TYPE)
  val repairType: DirectoryCassandraDto? = null,
  @CqlName(VIEW_FROM_WINDOW)
  val viewFromWindow: DirectoryCassandraDto? = null,
  @CqlName(CONVENIENCES)
  val conveniences: Set<DirectoryCassandraDto>? = null,
  @CqlName(APPLIANCES)
  val appliances: Set<DirectoryCassandraDto>? = null,
  @CqlName(RESIDENTS)
  val residents: Int? = null,
  @CqlName(NO_SMOKING)
  val noSmoking: Boolean? = null,
  @CqlName(NO_ANIMALS)
  val noAnimals: Boolean? = null,
  @CqlName(NO_CHILDREN)
  val noChildren: Boolean? = null,
  @CqlName(NO_PARTIES)
  val noParties: Boolean? = null,
  @CqlName(DESCRIPTION)
  val description: String? = null,
  @CqlName(PHOTOS)
  val photos: Set<MediaFileCassandraDto>? = null,
  @CqlName(LOCK_VERSION)
  val lockVersion: String? = null,
) {
  fun toModel() = FlatModel(
    id = id?.let { FlatIdModel(it) } ?: FlatModel.NONE.id,
    houseId = houseId?.let { HouseIdModel(it) } ?: FlatModel.NONE.houseId,
    number = number ?: FlatModel.NONE.number,
    area = area ?: FlatModel.NONE.area,
    areaLiving = areaLiving ?: FlatModel.NONE.areaLiving,
    areaKitchen = areaKitchen ?: FlatModel.NONE.areaKitchen,
    rooms = rooms ?: FlatModel.NONE.rooms,
    floor = floor ?: FlatModel.NONE.floor,
    ceilingHeight = ceilingHeight ?: FlatModel.NONE.ceilingHeight,
    residents = residents ?: FlatModel.NONE.residents,
    bedrooms = bedrooms ?: FlatModel.NONE.bedrooms,
    beds = beds ?: FlatModel.NONE.beds,
    bathrooms = bathrooms ?: FlatModel.NONE.bathrooms,
    bathroomType = (bathroomType?.toModel()) as BathroomTypeModel,
    balcony = balcony ?: FlatModel.NONE.balcony,
    loggia = loggia ?: FlatModel.NONE.loggia,
    repairType = (repairType?.toModel()) as RepairTypeModel,
    viewFromWindow = (viewFromWindow?.toModel()) as ViewFromWindowModel,
    conveniences = conveniences?.map { (it.toModel()) as ConvenienceModel }
      ?.toMutableSet() ?: FlatModel.NONE.conveniences,
    appliances = appliances?.map { (it.toModel()) as AppliancesModel }
      ?.toMutableSet() ?: FlatModel.NONE.appliances,
    noSmoking = noSmoking ?: FlatModel.NONE.noSmoking,
    noAnimals = noAnimals ?: FlatModel.NONE.noAnimals,
    noChildren = noChildren ?: FlatModel.NONE.noChildren,
    noParties = noParties ?: FlatModel.NONE.noParties,
    description = description ?: FlatModel.NONE.description,
    photos = photos?.map { it.toModel() }
      ?.toMutableSet() ?: FlatModel.NONE.photos,
  )

  companion object {
    const val TABLE_NAME = "flats_by_id"
    const val ID = "id"
    const val HOUSE_ID = "house_id"
    const val NUMBER = "number"
    const val AREA = "area"
    const val AREA_LIVING = "area_living"
    const val AREA_KITCHEN = "area_kitchen"
    const val ROOMS = "rooms"
    const val FLOOR = "floor"
    const val CEILING_HEIGHT = "ceiling_height"
    const val BEDROOMS = "bedrooms"
    const val BEDS = "beds"
    const val BATHROOMS = "bathrooms"
    const val BATHROOM_TYPE = "bathroom_type"
    const val BALCONY = "balcony"
    const val LOGGIA = "loggia"
    const val REPAIR_TYPE = "repair_type"
    const val VIEW_FROM_WINDOW = "view_from_window"
    const val CONVENIENCES = "conveniences"
    const val APPLIANCES = "appliances"
    const val RESIDENTS = "residents"
    const val NO_SMOKING = "no_smoking"
    const val NO_ANIMALS = "no_animals"
    const val NO_CHILDREN = "no_children"
    const val NO_PARTIES = "no_parties"
    const val DESCRIPTION = "description"
    const val PHOTOS = "photos"
    const val LOCK_VERSION = "lock_version"

    fun of(model: FlatModel) = of(model, model.id.id)

    fun of(model: FlatModel, id: String) = FlatByIdCassandraDto(
      id = id.takeIf { it != FlatModel.NONE.id.id },
      houseId = model.houseId.id.takeIf { it != FlatModel.NONE.houseId.id },
      number = model.number.takeIf { it != FlatModel.NONE.number },
      area = model.area.takeIf { it != FlatModel.NONE.area },
      areaLiving = model.areaLiving.takeIf { it != FlatModel.NONE.areaLiving },
      areaKitchen = model.areaKitchen.takeIf { it != FlatModel.NONE.areaKitchen },
      rooms = model.rooms.takeIf { it != FlatModel.NONE.rooms },
      floor = model.floor.takeIf { it != FlatModel.NONE.floor },
      ceilingHeight = model.ceilingHeight.takeIf { it != FlatModel.NONE.ceilingHeight },
      bedrooms = model.bedrooms.takeIf { it != FlatModel.NONE.bedrooms },
      beds = model.beds.takeIf { it != FlatModel.NONE.beds },
      bathrooms = model.bathrooms.takeIf { it != FlatModel.NONE.bathrooms },
      bathroomType = model.bathroomType.takeIf { it != FlatModel.NONE.bathroomType }
        ?.let { DirectoryCassandraDto.of(it) },
      balcony = model.balcony.takeIf { it != FlatModel.NONE.balcony },
      loggia = model.loggia.takeIf { it != FlatModel.NONE.loggia },
      repairType = model.repairType.takeIf { it != FlatModel.NONE.repairType }
        ?.let { DirectoryCassandraDto.of(it) },
      viewFromWindow = model.viewFromWindow.takeIf { it != FlatModel.NONE.viewFromWindow }
        ?.let { DirectoryCassandraDto.of(it) },
      conveniences = model.conveniences.takeIf { it != FlatModel.NONE.conveniences }
        ?.map { DirectoryCassandraDto.of(it) }?.toSet(),
      appliances = model.appliances.takeIf { it != FlatModel.NONE.appliances }
        ?.map { DirectoryCassandraDto.of(it) }?.toSet(),
      residents = model.residents.takeIf { it != FlatModel.NONE.residents },
      noSmoking = model.noSmoking.takeIf { it != FlatModel.NONE.noSmoking },
      noAnimals = model.noAnimals.takeIf { it != FlatModel.NONE.noAnimals },
      noChildren = model.noChildren.takeIf { it != FlatModel.NONE.noChildren },
      noParties = model.noParties.takeIf { it != FlatModel.NONE.noParties },
      description = model.description.takeIf { it != FlatModel.NONE.description },
      photos = model.photos.takeIf { it != FlatModel.NONE.photos }
        ?.map { MediaFileCassandraDto.of(it) }?.toSet(),
      lockVersion = UUID.randomUUID().toString(),
    )
  }
}
