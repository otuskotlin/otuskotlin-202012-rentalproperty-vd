package schema

import org.jetbrains.exposed.dao.id.UUIDTable

object DirectoriesTable : UUIDTable("directories") {
  val name = varchar("name", 256)
  val type = varchar("type", 256)
}
