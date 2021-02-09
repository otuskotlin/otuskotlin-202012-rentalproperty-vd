plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

group = "ru.otus.otuskotlin.vd.rentalproperty.mp"
version = "0.0.1"

kotlin {
  /* Targets configuration omitted.
  *  To find out how to configure the targets, please follow the link:
  *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
  js {
    browser { }
    nodejs { }
  }
  jvm {
    withJava()
  }

  sourceSets {
    val coroutinesVersion: String by project
    val serializationVersion: String by project

    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    val jsMain by getting {
      dependencies {
        implementation(kotlin("stdlib"))
      }
    }
    val jsTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-js"))
      }
    }

    val jvmMain by getting {
      dependencies {
        implementation(kotlin("stdlib"))
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
      }
    }
  }
}