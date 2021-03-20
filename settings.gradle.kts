rootProject.name = "ok-202012-rentalproperty-vd"

pluginManagement {
  plugins {
    val bmuschkoVersion: String by settings
    val kotlessVersion: String by settings
    val kotlinVersion: String by settings
    val openApiVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyVersion: String by settings

    kotlin("jvm") version kotlinVersion apply false
    kotlin("js") version kotlinVersion apply false
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false

    id("com.bmuschko.docker-java-application") version bmuschkoVersion apply false
    id("io.kotless") version kotlessVersion apply false
    id("io.spring.dependency-management") version springDependencyVersion
    id("org.openapi.generator") version openApiVersion apply false
    id("org.springframework.boot") version springBootVersion
  }
  repositories {
    gradlePluginPortal()
  }
}

include("rentalproperty-be-app-ktor")
include("rentalproperty-be-app-spring-cl")
include("rentalproperty-be-app-spring-fu")
include("rentalproperty-be-common")
include("rentalproperty-be-directory")
include("rentalproperty-be-mappers-mp")
include("rentalproperty-mp-transport-mp")
include("rentalproperty-be-transport-openapi")
