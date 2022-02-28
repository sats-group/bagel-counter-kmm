plugins {
  id("com.android.application")
  kotlin("android")
}

val composeVersion = "1.1.1"

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "com.sats.bagels.android"

    minSdk = 23
    targetSdk = 31

    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    compose = true
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }

  composeOptions {
    kotlinCompilerExtensionVersion = composeVersion
  }
}

dependencies {
  // Local
  implementation(project(":shared"))

  // Compose
  implementation("androidx.activity:activity-compose:1.4.0")
  implementation("androidx.compose.foundation:foundation:$composeVersion")
  implementation("androidx.compose.material:material:$composeVersion")
  implementation("androidx.compose.runtime:runtime:$composeVersion")
  implementation("androidx.compose.ui:ui:$composeVersion")

  // Material
  implementation("com.google.android.material:material:1.5.0")
}
