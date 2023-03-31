@file:Suppress( "DSL_SCOPE_VIOLATION")
// red underline should be fixed in Gradle 8.1 https://youtrack.jetbrains.com/issue/KTIJ-19369 / https://github.com/gradle/gradle/issues/23784
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.junit5)
}


android {
    namespace = "be.wimbervoets.microwaveoven"
    compileSdk = 33

    defaultConfig {
        applicationId = "be.wimbervoets.microwaveoven"
        minSdk = 28
        targetSdk  = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        apiVersion = "1.8" // Allow using declarations only from the specified version of bundled libraries
        languageVersion = "1.8" // Provide source compatibility with the specified version of Kotlin
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.android.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.timber)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui) // Text, ...
    implementation(libs.androidx.compose.material3) // Material3Theme
    implementation(libs.androidx.compose.ui.tooling) // @Preview compose
    implementation(libs.androidx.compose.activity) // setContent { .. } on ComponentActivity
    implementation(libs.androidx.lifecycle.runtime.compose) // Kotlin Flow to Compose State (collectAsStateWithLifecycle)

    // JUnit 5
    implementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // TestScope for Kotlin coroutines (Flow)
    testImplementation(libs.kotlinx.coroutines.test)

    // Testing Flows
    testImplementation(libs.turbine)

    // Mocks
    testImplementation(libs.mockk.android)
}