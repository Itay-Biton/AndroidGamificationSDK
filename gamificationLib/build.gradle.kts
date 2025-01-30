plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "com.gamificationlib"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.ItayBiton" 
                artifactId = "gamificationLib"
                version = "v1.0.0" 
                artifact(tasks.getByName("bundleReleaseAar"))

                pom {
                    withXml {
                        val dependenciesNode = asNode().appendNode("dependencies")
                        configurations.api.get().dependencies.forEach { dependency ->
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", dependency.group)
                            dependencyNode.appendNode("artifactId", dependency.name)
                            dependencyNode.appendNode("version", dependency.version)
                            dependencyNode.appendNode("scope", "compile")
                        }
                        configurations.implementation.get().dependencies.forEach { dependency ->
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", dependency.group)
                            dependencyNode.appendNode("artifactId", dependency.name)
                            dependencyNode.appendNode("version", dependency.version)
                            dependencyNode.appendNode("scope", "runtime")
                        }
                    }
                }
            }
        }
    }
}