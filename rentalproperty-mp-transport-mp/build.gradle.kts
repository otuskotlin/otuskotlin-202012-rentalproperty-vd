plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

group = "${rootProject.group}.transport.kmp.models"
version = "0.0.1"

kotlin {
  /* Targets configuration omitted.
  *  To find out how to configure the targets, please follow the link:
  *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
  js {
    browser {
      testTask {
        useKarma {
          useChromeHeadless()
        }
      }
      binaries.executable()
    }
  }

  jvm {
    withJava()
  }

  sourceSets {
    val serializationVersion: String by project

    val commonMain by getting {
      dependencies {
        implementation(project(":rentalproperty-be-directory"))

        implementation(kotlin("stdlib-common"))
        api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
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
        implementation(kotlin("stdlib-js"))
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