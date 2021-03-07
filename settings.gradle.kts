rootProject.name = "ok-202012-rentalproperty-vd"

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

include("rentalproperty-be-app-spring-cl")
include("rentalproperty-be-common")
include("rentalproperty-be-mappers-mp")
include("rentalproperty-mp")
include("rentalproperty-mp-transport-mp")
