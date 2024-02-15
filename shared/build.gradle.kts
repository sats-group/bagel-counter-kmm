plugins {
  kotlin("multiplatform")

  id("com.android.library")
}

kotlin {
  androidTarget()

  listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
      }
    }

    val androidMain by getting {
      dependencies {
        implementation("com.microsoft.signalr:signalr:6.0.1")
      }
    }

    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }
  }
}

android {
  compileSdk = 34

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

  defaultConfig {
    minSdk = 23
  }

  namespace = "com.sats.bagels"

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}
