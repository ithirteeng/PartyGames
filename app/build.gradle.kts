plugins {
    android
    `kotlin-android`
}

android {
    namespace = "com.ith.partygames"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ith.partygames"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // DI
    implementation(stack.koin.core)
    implementation(stack.koin.android)
    implementation(stack.koin.androidx.compose)

    //android
    implementation(stack.androidx.core.ktx)
    implementation(stack.androidx.lifecycle.runtime.ktx)
    implementation(stack.androidx.activity.compose)
    implementation(platform(stack.androidx.compose.bom))
    implementation(stack.androidx.ui)
    implementation(stack.androidx.ui.graphics)
    implementation(stack.androidx.ui.tooling.preview)
    implementation(stack.androidx.navigation.compose)
    implementation(stack.androidx.material3)

    //serialization
    implementation(stack.kotlinx.serialization.json)

    //storage
    implementation(stack.androidx.datastore)
    implementation(stack.androidx.datastore.preferences)

    implementation(project(":lib-meshrabiya-local"))
}