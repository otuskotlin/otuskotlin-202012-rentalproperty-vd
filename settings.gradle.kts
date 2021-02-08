rootProject.name = "otuskotlin-202012-rentalproperty-vd"

pluginManagement {
  val kotlinVersion: String by settings
  plugins {
    kotlin("jvm") version kotlinVersion
  }
  repositories {
    gradlePluginPortal()
  }
}

include("rentalproperty-spring")
include("rentalproperty-ktor")
