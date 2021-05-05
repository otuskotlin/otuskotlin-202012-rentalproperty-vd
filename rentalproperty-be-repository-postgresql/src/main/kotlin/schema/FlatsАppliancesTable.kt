package schema

import org.jetbrains.exposed.sql.Table

object FlatsАppliancesTable : Table() {
  val flat = reference("flat", FlatsTable)
  val appliance = reference("appliance", DirectoriesTable)
  override val primaryKey = PrimaryKey(flat, appliance, name = "PK_FlatsАppliances_flat_appliance")
}