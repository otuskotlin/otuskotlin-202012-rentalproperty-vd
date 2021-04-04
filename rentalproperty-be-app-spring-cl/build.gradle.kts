import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.3.8.RELEASE"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  kotlin("jvm")
  kotlin("plugin.spring") version "1.4.30"
  kotlin("plugin.allopen") version "1.4.30"
  kotlin("plugin.jpa") version "1.4.30"
  kotlin("kapt")
}

group = "${rootProject.group}.spring"
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  val kotestVersion: String by project

  implementation(project(":rentalproperty-be-business-logic"))
  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))
  implementation(project(":rentalproperty-be-mappers"))
  implementation(project(":rentalproperty-mp-common"))
  implementation(project(":rentalproperty-mp-transport"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-mustache")
  //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  //implementation("org.springframework.boot:spring-boot-starter-validation")

  implementation(kotlin("stdlib"))
  //implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  //implementation("org.liquibase:liquibase-core")
  //runtimeOnly("org.postgresql:postgresql")
  //runtimeOnly("com.h2database:h2")

  runtimeOnly("org.springframework.boot:spring-boot-devtools")

  kapt("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    exclude(group = "org.junit.jupiter", module = "junit-jupiter")
    exclude(module = "mockito-core")
  }

  testImplementation(kotlin("test-junit5"))
  testImplementation(platform("org.junit:junit-bom:5.6.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")

  //testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:jar:2.2.0")
  testImplementation("io.mockk:mockk:1.10.6")

  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest:kotest-property:$kotestVersion")
}

allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

tasks {
  test {
    useJUnitPlatform()
  }
}

tasks.withType<KotlinCompile>() {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  freeCompilerArgs = listOf("-Xinline-classes")
}