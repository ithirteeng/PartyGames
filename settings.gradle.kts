rootProject.name = "PartyGames"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven(url = "https://jitpack.io")
        jcenter()
    }
    versionCatalogs {
        register("stack") { from(files("./gradle/stack.versions.toml")) }
    }
}

apply(from = "common/settings-common.gradle.kts")
apply(from = "screens/settings-screens.gradle.kts")
include(":app")
include(":lib-meshrabiya-local")
