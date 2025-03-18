plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Hilt
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.kotlinKapt)
    // Navigation Component
    alias(libs.plugins.navigation.safe.args)
    // firebase auth
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.luminuses.easyshopmvvmcleanarch"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.luminuses.easyshopmvvmcleanarch"
        minSdk = 24
        targetSdk = 35
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //  Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // SSP-SDP library
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Retrofit
    implementation(libs.com.squareup.retrofit)

    // Retrofit with Moshi Converter
    implementation(libs.com.squareup.converter.moshi)

    // OkHttp
    implementation(libs.com.squareup.okhttp)

    // Moshi
    implementation(libs.com.squareup.moshi)

    //glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // firebase auth
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.firestore)
}