plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
  val kotestVersion: String by project
  val mockkVersion: String by project

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))

  implementation(kotlin("stdlib"))
  api("io.kotest:kotest-runner-junit5:$kotestVersion")
  api("io.mockk:mockk:$mockkVersion")
}

tasks {
  withType<Test> {
    useJUnitPlatform()
  }
}