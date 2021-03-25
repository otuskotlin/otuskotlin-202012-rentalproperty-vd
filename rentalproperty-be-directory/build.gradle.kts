import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

group = "${rootProject.group}.be.directory"
version = rootProject.version

dependencies {
  val serializationVersion: String by project

  implementation(kotlin("stdlib-jdk8"))
  api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
  api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  freeCompilerArgs = listOf("-Xinline-classes")
}