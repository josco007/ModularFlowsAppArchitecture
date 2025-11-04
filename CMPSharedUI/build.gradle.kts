import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"
    id("org.jetbrains.compose") version "1.9.2" // o la más reciente
    kotlin("plugin.serialization") version "2.2.21"
}

kotlin {
    androidTarget()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "CMPSharedUI"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.runtime:runtime:1.7.0")
                implementation("org.jetbrains.compose.foundation:foundation:1.7.0")
                implementation("org.jetbrains.compose.material3:material3:1.7.0")
                implementation("org.jetbrains.compose.ui:ui:1.7.0")
                implementation("media.kamel:kamel-image:0.9.5")
                implementation(project(mapOf("path" to ":KMMCoreKit")))
                implementation(compose.components.resources)

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.9.3")
                implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
                implementation("androidx.activity:activity-compose:1.9.3")
                implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
            }
        }
    }
}

android {
    namespace = "com.chihuasdevs.cmpsharedui"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
dependencies {
    implementation(project(mapOf("path" to ":KMMCoreKit")))
    // 🔹 Compose tooling (para previews y debug)
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.3")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
}
