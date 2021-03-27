plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))
  implementation(project(":rentalproperty-mp-transport"))

  implementation(kotlin("stdlib-jdk8"))
  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))
}
