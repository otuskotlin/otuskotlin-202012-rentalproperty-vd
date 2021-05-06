package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql.schema

import org.jetbrains.exposed.sql.Table

object FlatsMediaFilesTable : Table() {
  val flat = reference("flat", FlatsTable)
  val mediaFile = reference("mf", DirectoriesTable)
  override val primaryKey = PrimaryKey(flat, mediaFile, name = "PK_FlatsMediaFiles_flat_mf")
}