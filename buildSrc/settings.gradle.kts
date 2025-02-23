rootProject.name = "buildSrc"

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
        register("stack") { from(files("../gradle/stack.versions.toml")) }
    }
}
