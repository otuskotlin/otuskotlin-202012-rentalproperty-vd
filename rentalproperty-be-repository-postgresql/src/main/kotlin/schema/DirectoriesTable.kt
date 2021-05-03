package schema

import org.jetbrains.exposed.dao.id.UUIDTable

object DirectoriesTable : UUIDTable("directories") {
  val name = varchar("name", 50)
  val type = varchar("type", 50)
}
