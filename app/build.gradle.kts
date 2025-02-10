plugins {
    android
    `kotlin-android`
    `kotlinx-serialization`
    `kotlin-composecompiler`
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
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // DI
    implementation(stack.koin.core)
    implementation(stack.koin.android)
    implementation(stack.koin.androidx.compose)

    //UI
    implementation(stack.systemui.controller)

    // local
    implementation(project(":common:architecture"))
    implementation(project(":common:games"))
    implementation(project(":common:ui"))
    implementation(project(":lib-meshrabiya-local"))

    implementation(project(":screens:main"))
    implementation(project(":screens:common-connection"))
    implementation(project(":screens:decryptor-game"))

    //serialization
    implementation(stack.kotlinx.serialization.json)

    //storage
    implementation(stack.androidx.datastore)
    implementation(stack.androidx.datastore.preferences)

    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}