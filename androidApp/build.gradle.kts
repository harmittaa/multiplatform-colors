plugins {
    id("com.android.application")
    kotlin("android")
}

apply(from = "${project.rootDir}/ktlint.gradle.kts")

android {
    namespace = "com.harmittaa.multipaltformcolors.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.harmittaa.multipaltformcolors.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false 
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.bundles.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    testImplementation(libs.junit.android)
}