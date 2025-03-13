plugins {
    common.library
    `kotlinx-serialization`
}

android {
    namespace = "com.ith.partygames.common.server"
}

dependencies {
    api(stack.nano.httpd)

    implementation(project(":common:mesh-utils"))
    implementation(stack.koin.core)
    implementation(stack.koin.android)
}
