plugins {
  kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
  val kotlinVersion: String by project
  val logbackVersion: String by project
  val logbackEncoderVersion: String by project
  val logbackKafkaVersion: String by project
  val coroutinesVersion: String by project
  val janinoVersion: String by project

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

  // logback
  api("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion")
  implementation("com.github.danielwegener:logback-kafka-appender:$logbackKafkaVersion")
  implementation("org.codehaus.janino:janino:$janinoVersion")

  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))
}
