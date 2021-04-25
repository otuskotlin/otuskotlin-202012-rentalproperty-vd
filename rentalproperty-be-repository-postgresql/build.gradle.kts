plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
  val exposedVersion: String by project
  val testContainersVersion: String by project
  val postgresDriverVersion: String by project

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))

  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
  implementation("org.postgresql:postgresql:$postgresDriverVersion")

  testImplementation(kotlin("test-junit"))
  testImplementation("org.testcontainers:postgresql:$testContainersVersion")
}
