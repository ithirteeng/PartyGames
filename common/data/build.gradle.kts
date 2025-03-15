plugins {
    common.library
    `kotlinx-serialization`
}

android {
    namespace = "com.ith.partygames.common.data"
}

dependencies {
    api(stack.okhttp)
    api(stack.nano.httpd)

    implementation(stack.androidx.datastore)
    implementation(stack.androidx.datastore.preferences)
    implementation(stack.koin.android)
    implementation(stack.koin.core)
    implementation(stack.kotlinx.serialization.json)

    implementation(project(":common:mesh-utils"))
}
