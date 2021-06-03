plugins {
  application
  kotlin("jvm")
  id("com.bmuschko.docker-java-application")
}

group = "${rootProject.group}.be.ktor"
version = rootProject.version

dependencies {
  val ktorVersion: String by project
  val logbackVersion: String by project
  val serializationVersion: String by project
  val ktorKafkaVersion: String by project
  val kafkaVersion: String by project

  implementation(project(":rentalproperty-be-business-logic"))
  implementation(project(":rentalproperty-be-common"))
  implementation(project(":rentalproperty-be-directory"))
  implementation(project(":rentalproperty-be-logging"))
  implementation(project(":rentalproperty-be-mappers"))
  implementation(project(":rentalproperty-be-repository-inmemory"))
  implementation(project(":rentalproperty-be-repository-cassandra"))
  implementation(project(":rentalproperty-mp-common"))
  implementation(project(":rentalproperty-mp-transport"))

  implementation(kotlin("stdlib-jdk8"))

  implementation("io.ktor:ktor-auth:$ktorVersion")
  implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-host-common:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("io.ktor:ktor-websockets:$ktorVersion")

  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

  implementation("com.github.Datana-company:ktor-kafka:$ktorKafkaVersion")
  implementation("org.apache.kafka:kafka-clients:$kafkaVersion")

  testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

docker {
//  url = 'https://192.168.59.103:2376'
//  certPath = new File(System.properties['user.home'], '.boot2docker/certs/boot2docker-vm')

//    registryCredentials {
//        url.set(dockerParams.dockerUrl)
//        dockerParams.dockerUser?.also { username.set(it) }
//        dockerParams.dockerPass?.also { password.set(it) }
//    email = 'benjamin.muschko@gmail.com'
//    }

  javaApplication {
    baseImage.set("adoptopenjdk/openjdk11:alpine-jre")
    maintainer.set("(c) Otus")
    ports.set(listOf(8080))
    val imageName = project.name
    images.set(
      listOf(
        "$imageName:${project.version}",
        "$imageName:latest"
      )
    )
    jvmArgs.set(listOf("-Xms256m", "-Xmx512m"))
  }
}
