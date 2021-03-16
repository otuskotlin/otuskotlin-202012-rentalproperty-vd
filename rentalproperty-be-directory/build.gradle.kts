plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

group = "${rootProject.group}.be.directory"
version = rootProject.version

dependencies {
  val serializationVersion: String by project

  implementation(kotlin("stdlib"))
  api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
  api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
}
