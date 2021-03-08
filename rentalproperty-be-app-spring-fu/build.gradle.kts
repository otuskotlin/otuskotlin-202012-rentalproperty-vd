import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

group = "${rootProject.group}.be.spring.fu"
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  val springFuVersion: String by project
  val serializationVersion: String by project

  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-mappers-mp"))
  implementation(project(":rentalproperty-mp-transport-mp"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation(kotlin("stdlib"))

  implementation("org.springframework.fu:spring-fu-kofu:$springFuVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
  implementation("org.springframework:spring-webmvc")

  testImplementation(kotlin("test-junit5"))
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}