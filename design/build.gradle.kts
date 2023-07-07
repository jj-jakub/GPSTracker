plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion= "1.4.7"
    }
    namespace = "com.jj.templateproject.design"
}

dependencies {
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.1")

    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")
}