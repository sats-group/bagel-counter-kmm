plugins {
  id("com.android.application")
  kotlin("android")
}

val composeVersion = "1.6.1"

android {
  compileSdk = 34

  defaultConfig {
    applicationId = "com.sats.bagels.android"

    minSdk = 23
    targetSdk = 34

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
    kotlinCompilerExtensionVersion = "1.5.9"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  namespace = "com.sats.bagels.android"
}

dependencies {
  // Local
  implementation(project(":shared"))

  // Compose
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation("androidx.compose.foundation:foundation:$composeVersion")
  implementation("androidx.compose.material:material:$composeVersion")
  implementation("androidx.compose.material:material-icons-extended:$composeVersion")
  implementation("androidx.compose.runtime:runtime:$composeVersion")
  implementation("androidx.compose.ui:ui:$composeVersion")

  // Material
  implementation("com.google.android.material:material:1.11.0")
}
