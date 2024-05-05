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

rootProject.name = "KMMRepositoryKit"
include(":KMMRepositoryKitShared")


include(":KMMCoreKit")
project(":KMMCoreKit").projectDir = file("../KMMCoreKit/KMMCoreKit")