plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-mp-transport-mp"))
}
