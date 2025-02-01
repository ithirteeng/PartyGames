plugins {
    common.library
    `kotlin-composecompiler`
}

android {
    namespace = "com.ith.partygames.common.ui"
}

dependencies {
    api(stack.androidx.core.ktx)
    api(stack.androidx.lifecycle.runtime.ktx)
    api(stack.androidx.activity.compose)
    api(platform(stack.androidx.compose.bom))
    api(stack.androidx.ui)
    api(stack.androidx.ui.graphics)
    api(stack.androidx.ui.tooling.preview)
    api(stack.androidx.material3)
    api(stack.android.material)
}