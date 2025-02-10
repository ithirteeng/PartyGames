plugins {
    common.library
    `kotlinx-serialization`
}

android {
    namespace = "com.ith.partygames.common.games"
}

dependencies {
    implementation(stack.kotlinx.serialization.json)
}
