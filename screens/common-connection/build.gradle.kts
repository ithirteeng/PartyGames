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
    implementation(project(":common:architecture"))
    implementation(project(":common:games"))
    implementation(project(":common:mesh-utils"))
    implementation(project(":common:ui"))
    implementation(project(":common:data"))
    implementation(project(":common:server"))

    implementation(stack.koin.core)
    implementation(stack.koin.android)
    implementation(stack.koin.androidx.compose)
    implementation(stack.kotlinx.serialization.json)
    implementation(stack.androidx.datastore.preferences)
    implementation(stack.androidx.datastore)
    implementation(stack.qrcode.scanner)
}
