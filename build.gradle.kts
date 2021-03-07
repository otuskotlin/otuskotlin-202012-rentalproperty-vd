plugins {
  kotlin("jvm") apply false
  kotlin("js") apply false
  kotlin("multiplatform") apply false
}

group = "ru.otus.otuskotlin.vd.rentalproperty"
version = "0.0.1"

subprojects {
  group = rootProject.group
  version = rootProject.version

  repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { url = uri("https://repo.spring.io/milestone") }
  }

}