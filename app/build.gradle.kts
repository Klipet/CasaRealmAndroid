plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("io.realm.kotlin")
}

android {
    namespace = "com.example.casarealmandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.casarealmandroid"
        minSdk = 24
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
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    //realm
    implementation ("io.realm:realm-gradle-plugin:10.11.0")
    implementation ("io.realm:realm-android-library:10.9.0")
    implementation ("io.realm.kotlin:library-base:0.10.0")
    implementation ("io.realm.kotlin:library-sync:1.11.0")
    kapt ("io.realm:realm-annotations:10.16.1")
    implementation ("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.3")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //vp2
    implementation ("androidx.viewpager2:viewpager2:1.1.0-beta02")
    implementation ("com.google.android.material:material:1.10.0")

    //okhttp
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    //retrogit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}