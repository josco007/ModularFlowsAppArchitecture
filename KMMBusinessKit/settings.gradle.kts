enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KMMBusinessKit"
include(":KMMBusinessKitShared")

include(":KMMCoreKit")
project(":KMMCoreKit").projectDir = file("../KMMCoreKit/KMMCoreKit")

include(":KMPUnitTestKit")
project(":KMPUnitTestKit").projectDir = file("../KMPUnitTestKit/shared")