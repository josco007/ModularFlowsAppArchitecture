plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {

        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "KMPSharedAppKit"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(project(mapOf("path" to ":KMMCoreKit")))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
        }
        commonTest.dependencies {
            implementation(project(mapOf("path" to ":KMMCoreKit")))
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.chihuasdevs.kmpsharedappkit"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
