enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://jogamp.org/deployment/maven")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jogamp.org/deployment/maven")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

rootProject.name = "Balary"
include(":androidApp")
include(":composeApp")
include(":models")
include(":store")
