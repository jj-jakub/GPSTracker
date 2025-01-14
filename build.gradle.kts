plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.composeCompiler).apply(false)

    val kotlinVersion = libs.versions.kotlin.get()
    kotlin("plugin.serialization") version kotlinVersion apply false
}

buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}