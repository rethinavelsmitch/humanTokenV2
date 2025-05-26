import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }


    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

//    sourceSets {
//        commonMain.dependencies {
//        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
//    }


    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {

            dependencies {
                implementation("org.jetbrains.compose.runtime:runtime:1.8.1")
                implementation("org.jetbrains.compose.foundation:foundation:1.8.1")
                implementation("org.jetbrains.compose.material3:material3:1.8.1")
            }


        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.8.2")
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
            }
        }
        // iOS targets similarly...
    }


}

android {

    namespace = "com.deepholistics"

    compileSdk = 35
    defaultConfig {
        minSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
}
