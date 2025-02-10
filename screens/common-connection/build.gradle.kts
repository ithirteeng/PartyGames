plugins {
    common.library
    `kotlinx-serialization`
    `kotlin-composecompiler`
}
android {
    namespace = "com.ith.partygames.screens.common_connection"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":common:ui"))
    implementation(project(":common:architecture"))
    implementation(project(":common:games"))

    implementation(stack.koin.core)
    implementation(stack.koin.android)
    implementation(stack.koin.androidx.compose)
    implementation(stack.kotlinx.serialization.json)
}