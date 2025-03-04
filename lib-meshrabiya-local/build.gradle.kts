plugins {
    `android-library`
    `kotlin-android`
    `kotlinx-serialization`
}

android {
    namespace = "com.ustadmobile.httpoverbluetooth"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":common:games"))

    implementation(stack.androidx.core.ktx)
    implementation(stack.androidx.appcompat)
    implementation(stack.rawhttp.core)
    implementation(stack.kotlinx.serialization.json)
    implementation(stack.androidx.datastore.preferences)

    implementation(stack.org.bouncycastle.bcprov)
    implementation(stack.org.bouncycastle.bcpkix)

    implementation(stack.seancfoley.ipaddress)
}
