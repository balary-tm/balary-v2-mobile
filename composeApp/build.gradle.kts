plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.10"
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "composeApp"
            isStatic = false
            // Required when using NativeSQLiteDriver
            linkerOpts.add("-lsqlite3")
        }
        pod("MapLibre") {
            version = "6.4.2"
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.navigator)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.koin.coroutines)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.client.cia)
            implementation(libs.ktor.client.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.webview)
            implementation(libs.napier)
            implementation(libs.ktor.client.logging)
            implementation(libs.setting)
            implementation(libs.setting.noargs)
            implementation(libs.setting.serialization)
            implementation(libs.setting.coroutines)
            // Required
            implementation(libs.lyricist)

            // coil image load
            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)

            implementation(libs.kotlinx)

            // player
            implementation(libs.mediaplayer)

            // file picker
            implementation(libs.filekit.core)
            implementation(libs.filekit.compose)

            // viewmodel
            implementation(libs.viewmodel)

            // markdown
            implementation(libs.markdown)
            implementation(libs.markdown.m3)

            // shimmer
            implementation(libs.shimmer)

            // room database
            implementation(libs.androidx.paging.common)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)

            // reflect
            implementation(kotlin("reflect"))

            // toast
            implementation(libs.toast)

            // lottie
            implementation(libs.compottie)
//            implementation(libs.compottie.network)
            implementation(libs.compottie.resources)
            implementation(libs.compottie.dot)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.okhttp)

            // google maps
            // Google maps for Android
            implementation(libs.google.play.services.android.location)
            api(libs.google.play.services.android.maps)  // api means its exposed to the pure-android app (for init)
            // Google maps for Compose for Android
            implementation(libs.google.maps.android.compose)
            // Clustering
            implementation(libs.google.maps.android.compose.utils)

            // PDF
            implementation(libs.bouquet)

            // maplibre
            implementation(libs.maplibre)

            implementation(libs.androidx.room.paging)

        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.lyricist.processor)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

ksp {
    arg("lyricist.internalVisibility", "true")
    arg("lyricist.generateStringsProperty", "true")
}

android {
    namespace = "tm.com.balary"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}