plugins {
  kotlin("js") apply false
  kotlin("jvm") apply false
  kotlin("multiplatform") apply false

  id("org.openapi.generator") apply false
  id("com.bmuschko.docker-java-application") apply false
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
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://repo.spring.io/milestone") }
  }

  plugins.withId("org.jetbrains.kotlin.jvm") {
    tasks {
      withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
          freeCompilerArgs = listOf("-Xjsr305=strict")
          jvmTarget = "11"
        }
      }
    }
  }
}