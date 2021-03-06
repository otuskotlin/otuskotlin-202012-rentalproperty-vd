plugins {
  id("org.openapi.generator")
  java
  kotlin("jvm")
}

dependencies {
  val jacksonVersion: String by project
//  val logbackVersion: String by project
  val validationVersion: String by project

  implementation(kotlin("stdlib-jdk8"))
//    implementation("io.ktor:ktor-metrics:$ktorVersion")
//  implementation("io.ktor:ktor-client-apache:$ktorVersion")
//  implementation("io.ktor:ktor-client-core:$ktorVersion")
//  implementation("io.ktor:ktor-gson:$ktorVersion")
//  implementation("io.ktor:ktor-locations:$ktorVersion")
//  implementation("io.ktor:ktor-server-netty:$ktorVersion")
//  implementation("ch.qos.logback:logback-classic:$logbackVersion")

  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("javax.validation:validation-api:$validationVersion")

  testImplementation(kotlin("test-junit"))
}

openApiGenerate {
  val basePackage = "${project.group}.be.transport.openapi"
  packageName.set(basePackage)
  generatorName.set("kotlin-server")
  configOptions.apply {
//        put("library", "jvm-okhttp4")
//        put("requestDateConverter", "toString")
  }
//  globalProperties.apply {
//    put("models", "")
//    put("modelDocs", "false")
//        put("invoker", "false")
//        put("apis", "false")
//  }
  inputSpec.set("${rootProject.projectDir}/specs/rentalproperty-all.yaml")
  inputSpec.set("${rootProject.projectDir}/specs/rentalproperty-directory-api.yaml")
}

sourceSets {
  main {
    java.srcDirs("$buildDir/generate-resources/src/main/java")
  }
  test {
    java.srcDirs("$buildDir/generate-resources/src/main/java")
  }
}

tasks {
  compileKotlin.get().dependsOn(openApiGenerate)
}
