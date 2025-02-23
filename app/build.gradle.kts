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
    implementation(project(":common:architecture"))
    implementation(project(":common:data"))
    implementation(project(":common:games"))
    implementation(project(":common:ui"))

    implementation(project(":lib-meshrabiya-local"))

    implementation(project(":screens:common-connection"))
    implementation(project(":screens:decryptor-game"))
    implementation(project(":screens:main"))

    implementation(stack.androidx.datastore)
    implementation(stack.androidx.datastore.preferences)

    implementation(stack.koin.android)
    implementation(stack.koin.androidx.compose)
    implementation(stack.koin.core)

    implementation(stack.kotlinx.serialization.json)

    implementation(stack.systemui.controller)
    implementation(stack.qrcode.scanner)
}
