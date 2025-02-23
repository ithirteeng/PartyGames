plugins {
    common.library
    `kotlin-composecompiler`
}

android {
    namespace = "com.ith.partygames.common.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(stack.androidx.compose.bom))
    api(stack.android.material)
    api(stack.androidx.activity.compose)
    api(stack.androidx.core.ktx)
    api(stack.androidx.lifecycle.runtime.ktx)
    api(stack.androidx.compose.material3)
    api(stack.androidx.navigation.compose)
    api(stack.androidx.compose.ui)
    api(stack.androidx.compose.ui.graphics)
    api(stack.androidx.compose.ui.tooling.preview)
}
