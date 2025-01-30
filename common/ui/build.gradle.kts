plugins {
    common.library
}

android {
    namespace = "com.ith.partygames.common.ui"
}

dependencies {
    implementation(stack.androidx.core.ktx)
    implementation(stack.androidx.lifecycle.runtime.ktx)
    implementation(stack.androidx.activity.compose)
    implementation(platform(stack.androidx.compose.bom))
    implementation(stack.androidx.ui)
    implementation(stack.androidx.ui.graphics)
    implementation(stack.androidx.ui.tooling.preview)
}