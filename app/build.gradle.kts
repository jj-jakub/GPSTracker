plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-kapt")
    id("org.sonarqube") version "4.2.1.3168"
}

sonar {
    properties {
        property("sonar.projectKey", "jj-jakub_TemplateProject")
        property("sonar.organization", "jj-jakub")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

fun getCurrentRevisionHash(): String {
    val stdout = `java.io`.ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().substring(0, 8)
}

val propertiesFile = rootProject.file("./local.properties")
val properties = `java.util`.Properties()

try {
    properties.load(`java.io`.FileInputStream(propertiesFile))
} catch (e: Exception) {
    println("Exception occurred while loading propertiesFile, $e")
}

val ciBuildNumber = properties["ciBuildNumber"] ?: 0

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jj.gpstracker"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"

        buildConfigField("String", "currentRevisionHash", "\"${getCurrentRevisionHash()}\"")
        buildConfigField("int", "ciBuildNumber", "$ciBuildNumber")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
    signingConfigs {
        create("release") {
            val keystoreFile =
                file(System.getProperty("user.home") + "/work/_temp/keystore/GPSTracker.jks")
            storeFile = file(keystoreFile.path)
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false

            buildConfigField("String", "ServerBaseUrl", "\"https://www.google.com\"")
            buildConfigField(
                "String",
                "adMainBannerViewAdUnitId",
                "\"ca-app-pub-3940256099942544/6300978111\""
            )
            buildConfigField(
                "String",
                "adInterstitialAdUnitId",
                "\"ca-app-pub-3940256099942544/1033173712\""
            )
            manifestPlaceholders["admobId"] = "ca-app-pub-7809340407306359~6994189425"
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")

            buildConfigField("String", "licensingBase64PublicKey", "\"\"")
            buildConfigField("String", "ServerBaseUrl", "\"https://www.google.com\"")
            buildConfigField(
                "String",
                "adMainBannerViewAdUnitId",
                "\"ca-app-pub-3940256099942544/6300978111\""
            )
            buildConfigField(
                "String",
                "adInterstitialAdUnitId",
                "\"ca-app-pub-3940256099942544/1033173712\""
            )
            manifestPlaceholders["admobId"] = "ca-app-pub-7809340407306359~6994189425"
        }
    }

    flavorDimensions.add("version")
    productFlavors {
        create("flavor1") {
            applicationIdSuffix = ".fl1"
        }
        create("flavor2") {
            applicationIdSuffix = ".fl2"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    namespace = "com.jj.gpstracker"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":design"))
    implementation(project(":networking"))

    implementation(libs.kotlinStdlib)
    implementation(libs.koin)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.lifecycleLiveData)
    implementation(libs.navigationKtx)
    implementation(libs.koinCompose)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAnalytics)
    implementation(libs.firebaseMessaging)
    implementation(libs.firebaseCrashlytics)
    implementation(libs.accompanistPermissions)

    implementation(libs.googleAds)

    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeNavigation)
    implementation(libs.composePreview)
    implementation(libs.composeActivity)
    implementation(libs.accompanistSystemUiController)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit4)
    testImplementation(libs.junit5)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.koinTest)
    testImplementation(libs.koinTestJUnit5)
    testImplementation(libs.androidxNavTesting)
    testImplementation(libs.robolectric)
    testImplementation(libs.ui.test.junit4.android)
    testRuntimeOnly(libs.junitVintageEngine)

    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(libs.konsist)

    androidTestImplementation(libs.mockkAndroid)
    androidTestImplementation(libs.androidTestRules)
    androidTestImplementation(libs.uiAutomator)
    androidTestImplementation(libs.ui.test.junit4.android)
}