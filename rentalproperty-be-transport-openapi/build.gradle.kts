plugins {
    kotlin("jvm")
    id("org.openapi.generator")
}

group = rootProject.group
version = rootProject.version

dependencies {
    val ktorVersion: String by project
    val logbackVersion: String by project

    implementation(kotlin("stdlib-jdk8"))
//    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

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
    globalProperties.apply {
        put("models", "")
        put("modelDocs", "false")
        put("invoker", "false")
        put("apis", "false")
    }
    inputSpec.set("${rootProject.projectDir}/specs/rentalproperty-base-api.yaml")
}

sourceSets.main {
    java.srcDirs("$buildDir/generate-resources/main/src/main/kotlin")
}

tasks {
    compileKotlin.get().dependsOn(openApiGenerate)
}
