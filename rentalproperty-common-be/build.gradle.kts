import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
  mavenCentral()
}

dependencies {
  val kotestVersion: String by project
  val coroutinesVersion: String by project

  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

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

tasks.test {
  useJUnit()
}

tasks.withType<KotlinCompile>() {
  kotlinOptions.jvmTarget = "11"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  freeCompilerArgs = listOf("-Xinline-classes")
}