plugins {
  kotlin("jvm")
}

group = "${rootProject.group}.business.logic.backend"
version = rootProject.version

dependencies {
  val kotestVersion: String by project

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))
  implementation(project(":rentalproperty-mp-common"))
  implementation(project(":rentalproperty-mp-pipelines"))
  implementation(project(":rentalproperty-mp-pipelines-validation"))

  implementation(kotlin("stdlib-jdk8"))

  testImplementation(kotlin("test-junit5"))
  testImplementation(platform("org.junit:junit-bom:5.7.1"))
  testImplementation("org.junit.jupiter:junit-jupiter")

  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks {
  test {
    useJUnitPlatform()
  }
}