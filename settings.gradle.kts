rootProject.name = "otuskotlin-202012-rentalproperty-vd"

pluginManagement {
  plugins {
    val kotlinVersion: String by settings
    kotlin("jvm") version kotlinVersion apply false
    kotlin("js") version kotlinVersion apply false
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
  }
  repositories {
    gradlePluginPortal()
  }
}

include("rentalproperty-common-be")
include("rentalproperty-mappers")
include("rentalproperty-mp")
include("rentalproperty-spring")
include("rentalproperty-transport-mp")
