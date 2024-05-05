import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization").version("1.9.23")
}

kotlin {


    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "KMMCoreKitShared"
            xcf.add(this)
            isStatic = true
        }
    }

    val ktorVersion = "2.3.7"
    val dateTimeVersion = "0.4.1"
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation ("com.benasher44:uuid:0.8.4")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.chihuasdevs.kmmCoreKit"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
