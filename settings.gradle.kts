pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        register("stack") { from(files("./gradle/stack.versions.toml")) }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://devserver3.ustadmobile.com/maven2/")
    }
}

rootProject.name = "WifiDirectPrototype"
include(":app")
include(":lib-meshrabiya-local")