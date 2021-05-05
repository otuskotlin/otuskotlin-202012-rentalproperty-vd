package schema

import org.jetbrains.exposed.dao.id.UUIDTable

object MediaFilesTable : UUIDTable("media_files") {
  val title = varchar("title", 256)
  val url = text("url")
  val fileNameInStorage = varchar("filename", 256)
}
