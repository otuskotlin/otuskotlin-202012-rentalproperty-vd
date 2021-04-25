package schema

import org.jetbrains.exposed.dao.id.UUIDTable

object MediaFilesTable : UUIDTable("media_files") {
  val title = text("title")
  val url = text("url")
  val fileNameInStorage = varchar("filename", 256)
}
