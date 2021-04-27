plugins {
  kotlin("jvm")
  kotlin("kapt")
}

group = rootProject.group
version = rootProject.version

dependencies {
  val cassandraDriverVersion: String by project
  val coroutinesVersion: String by project
  val testContainersVersion: String by project

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))

  implementation(kotlin("stdlib-jdk8"))
  implementation("com.datastax.oss:java-driver-core:$cassandraDriverVersion")
  implementation("com.datastax.oss:java-driver-query-builder:$cassandraDriverVersion")
  implementation("com.datastax.oss:java-driver-mapper-runtime:$cassandraDriverVersion")
  kapt("com.datastax.oss:java-driver-mapper-processor:$cassandraDriverVersion")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.3.8")

  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))
  testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
}