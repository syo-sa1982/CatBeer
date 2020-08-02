val kotlin_version: String by project
val coroutines_version by extra("1.3.6") //Kotlin coroutines用ライブラリ(async, await)のバージョン
val okhttp_version by extra("4.2.0")
val retrofit_version by extra("2.6.1")
val moshi_version by extra("1.8.0")
val koin_version by extra("2.0.0")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    //Deploygate
    id("deploygate")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.syousa1982.catbeer"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
//            signingConfig signingConfigs.debug
            isMinifyEnabled = false
            isShrinkResources = false
//            useProguard false
        }
        getByName("release") {
//            signingConfig signingConfigs.release
            isMinifyEnabled = true
            isShrinkResources = true
//            useProguard true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    // ビルド環境を設定
    flavorDimensions("default")
    productFlavors {
        // 本番環境
        create("production") {
            resValue("string", "app_name", "猫Bot")
            buildConfigField("String", "API_ENDPOINT", "\"https://us-central1-catbeer-5ae80.cloudfunctions.net\"")
        }
        create("development") {
            resValue("string", "app_name", "猫Bot 開発")
            buildConfigField("String", "API_ENDPOINT", "\"https://us-central1-catbeer-5ae80.cloudfunctions.net\"")
        }
    }
    compileOptions {
        setSourceCompatibility(1.8)
        setTargetCompatibility(1.8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.core:core-ktx:1.3.1")
    implementation ("com.google.android.material:material:1.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation ("androidx.navigation:navigation-fragment:2.3.0")
    implementation ("androidx.navigation:navigation-ui:2.3.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // network
    implementation ("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttp_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    // Json
    implementation ("com.squareup.moshi:moshi:$moshi_version")
    implementation ("com.squareup.moshi:moshi-adapters:$moshi_version")

    // DI
    implementation ("org.koin:koin-androidx-scope:$koin_version")
    implementation ("org.koin:koin-androidx-viewmodel:$koin_version")

    // firebase
    implementation ("com.google.firebase:firebase-core:17.4.4")

    // test
    testImplementation ("junit:junit:4.13")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")

}