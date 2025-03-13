plugins {
    common.library
    `kotlinx-serialization`
}

android {
    namespace = "com.ith.partygames.common.data"
}

dependencies {
    api(stack.okhttp)
    implementation(project(":common:mesh-utils"))

    implementation(stack.koin.core)
    implementation(stack.koin.android)
    implementation(stack.kotlinx.serialization.json)
    implementation(stack.androidx.datastore.preferences)
    implementation(stack.androidx.datastore)
}
