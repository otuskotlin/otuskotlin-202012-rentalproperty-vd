package schema

import org.jetbrains.exposed.dao.id.UUIDTable

object FlatsTable : UUIDTable("flats") {
  val houseId = varchar("house_id", 256)
  val number = varchar("number", 30)
  val area = double("area")
  val areaLiving = double("area_living")
  val areaKitchen = double("area_kitchen")
  val rooms = integer("rooms")
  val floor = integer("floor")
  val ceilingHeight = double("ceiling_height")
  val bedrooms = integer("bedrooms")
  val beds = integer("beds")
  val bathroom = integer("bathroom")
  val bathroomType = reference("bathroom_type_id", DirectoriesTable)
  val balcony = integer("balcony")
  val loggia = integer("loggia")
  val repairType = reference("repair_type_id", DirectoriesTable)
  val viewFromWindow = reference("view_from_window_id", DirectoriesTable)

  //TODO one-to-many
  val conveniences = reference("convenience_id", DirectoriesTable)

  //TODO one-to-many
  val appliances = reference("appliance_id", DirectoriesTable)
  val residents = integer("residents")
  val noSmoking = bool("noSmoking")
  val noAnimals = bool("noAnimals")
  val noChildren = bool("noChildren")
  val noParties = bool("noParties")
  val description = text("description")

  //TODO one-to-many
  val photos = reference("photo_id", MediaFilesTable)
}
