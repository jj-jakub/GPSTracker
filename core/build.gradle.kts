plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 23
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
    namespace = "com.jj.gpstracker.core"
}

dependencies {
    implementation(project (":domain"))
    implementation(project (":networking"))
    implementation(libs.bolts.tasks)

    implementation(libs.koin)
}