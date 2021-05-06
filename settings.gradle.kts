rootProject.name = "ok-202012-rentalproperty-vd"

pluginManagement {
  plugins {
    val bmuschkoVersion: String by settings
    val kotlessVersion: String by settings
    val kotlinVersion: String by settings
    val openApiVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyVersion: String by settings

    kotlin("jvm") version kotlinVersion
    kotlin("js") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("multiplatform") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("com.bmuschko.docker-java-application") version bmuschkoVersion
    id("io.kotless") version kotlessVersion apply false
    id("io.spring.dependency-management") version springDependencyVersion
    id("org.openapi.generator") version openApiVersion
    id("org.springframework.boot") version springBootVersion
  }
  repositories {
    gradlePluginPortal()
  }
}

include("rentalproperty-be-app-ktor")
include("rentalproperty-be-app-spring-cl")
include("rentalproperty-be-app-spring-fu")
include("rentalproperty-be-business-logic")
include("rentalproperty-be-common")
include("rentalproperty-be-directory")
include("rentalproperty-be-mappers")
include("rentalproperty-be-repository-cassandra")
include("rentalproperty-be-repository-inmemory")
include("rentalproperty-be-repository-postgresql")
include("rentalproperty-be-repository-tests")
include("rentalproperty-be-transport-openapi")
include("rentalproperty-mp-common")
include("rentalproperty-mp-pipelines")
include("rentalproperty-mp-pipelines-validation")
include("rentalproperty-mp-transport")
