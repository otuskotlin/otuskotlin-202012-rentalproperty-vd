import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("io.spring.dependency-management")
  id("org.springframework.boot")
}

group = "${rootProject.group}.be.spring.fu"
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  val coroutinesVersion: String by project
  val springFuVersion: String by project
  val serializationVersion: String by project

  implementation(project(":rentalproperty-be-business-logic"))
  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))
  implementation(project(":rentalproperty-be-mappers-mp"))
  implementation(project(":rentalproperty-mp-common"))
  implementation(project(":rentalproperty-mp-transport-mp"))

  implementation(kotlin("stdlib-jdk8"))

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutinesVersion")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.fu:spring-fu-kofu:$springFuVersion")
  implementation("org.springframework:spring-webmvc")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

  testImplementation(kotlin("test-junit5"))
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "11"
    }
  }

  withType<Test> {
    useJUnitPlatform()
  }

  bootBuildImage {
    imageName = "rentalproperty-app-spring-fu:${rootProject.version}"
  }
}