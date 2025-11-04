/*

🚀 Cómo usarlo
🔹 Generar versión Debug (para desarrollo / simulador)
./gradlew :KMPSharedAppKit:assembleDebugXCFramework


📂 Resultado:

KMPSharedAppKit/build/XCFrameworks/debug/KMPSharedAppKit.xcframework

🔹 Generar versión Release (para distribución / producción)
./gradlew :KMPSharedAppKit:assembleReleaseXCFramework


📂 Resultado:

KMPSharedAppKit/build/XCFrameworks/release/KMPSharedAppKit.xcframework

 */


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget()

    // --- Targets iOS ---
    val iosTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "KMPSharedAppKit"
            isStatic = true // o false si prefieres dinámico
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":KMMCoreKit"))
                implementation(project(":CMPSharedUI"))
                implementation(project(":KMMBusinessKit"))
                implementation(project(":KMMRepositoryKit"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":KMMCoreKit"))
                implementation(libs.kotlin.test)
            }
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

// ===================================================
// 🔧 CONFIGURACIÓN DE TAREAS PARA XCFRAMEWORKS
// ===================================================

// --- 🔹 Función auxiliar para crear la tarea ---
fun registerXCFrameworkTask(
    buildType: String,
    outputDirName: String
) {
    tasks.register("assemble${buildType.capitalize()}XCFramework") {
        group = "build"
        description = "Assembles a $buildType XCFramework for iOS (simulator + device)"

        dependsOn(
            ":KMPSharedAppKit:link${buildType.capitalize()}FrameworkIosArm64",
            ":KMPSharedAppKit:link${buildType.capitalize()}FrameworkIosSimulatorArm64",
            ":KMPSharedAppKit:link${buildType.capitalize()}FrameworkIosX64"
        )

        doLast {
            val buildDir = layout.buildDirectory.get().asFile
            val outputDir = File(buildDir, "XCFrameworks/$outputDirName")
            outputDir.mkdirs()

            val frameworks = listOf(
                File(buildDir, "bin/iosArm64/${buildType.lowercase()}Framework/KMPSharedAppKit.framework"),
                File(buildDir, "bin/iosSimulatorArm64/${buildType.lowercase()}Framework/KMPSharedAppKit.framework"),
                File(buildDir, "bin/iosX64/${buildType.lowercase()}Framework/KMPSharedAppKit.framework")
            )

            exec {
                commandLine(
                    "xcodebuild",
                    "-create-xcframework",
                    "-framework", frameworks[0].absolutePath,
                    "-framework", frameworks[1].absolutePath,
                    "-framework", frameworks[2].absolutePath,
                    "-output", File(outputDir, "KMPSharedAppKit.xcframework").absolutePath
                )
            }

            println("✅ XCFramework ($buildType) generado en: $outputDir")
        }
    }
}

// --- 🔹 Registrar las dos tareas ---
registerXCFrameworkTask("debug", "debug")
registerXCFrameworkTask("release", "release")

// ===================================================
// (Opcional) Copiar automáticamente el framework a iOS
// ===================================================

tasks.register<Copy>("exportReleaseXCFrameworkToIOS") {
    dependsOn("assembleReleaseXCFramework")
    from("$buildDir/XCFrameworks/release/KMPSharedAppKit.xcframework")
    into("$rootDir/iosApp/Frameworks")
}

tasks.register<Copy>("exportDebugXCFrameworkToIOS") {
    dependsOn("assembleDebugXCFramework")
    from("$buildDir/XCFrameworks/debug/KMPSharedAppKit.xcframework")
    into("$rootDir/iosApp/Frameworks")
}
