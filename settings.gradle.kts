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

rootProject.name = "AndroidWorkSpace"

include(":KMMCoreKit")
project(":KMMCoreKit").projectDir = file("KMMCoreKit/KMMCoreKit")

include(":KMMBusinessKit")
project(":KMMBusinessKit").projectDir = file("KMMBusinessKit/KMMBusinessKitShared")

include(":KMMRepositoryKit")
project(":KMMRepositoryKit").projectDir = file("KMMRepositoryKit/KMMRepositoryKitShared")

include(":KMPUnitTestKit")
project(":KMPUnitTestKit").projectDir = file("KMPUnitTestKit/shared")

include(":AndroidUI")
project(":AndroidUI").projectDir = file("AndroidUI")

include(":AndroidApp")
project(":AndroidApp").projectDir = file("AndroidApp/app")


include(":CMPSharedUI")
project(":CMPSharedUI").projectDir = file("CMPSharedUI")
