plugins {
  kotlin("jvm") version "1.4.21"
}

group = "ru.otus.otuskotlin.vd.rentalproperty"
version = "0.0.1"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))

  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))

}

tasks.test {
  useJUnit()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
  kotlinOptions.jvmTarget = "11"
}