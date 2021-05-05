package schema

import org.jetbrains.exposed.sql.Table

object FlatsConveniencesTable : Table() {
  val flat = reference("flat", FlatsTable)
  val convenience = reference("convenience", DirectoriesTable)
  override val primaryKey = PrimaryKey(flat, convenience, name = "PK_FlatsConveniences_flat_convenience")
}