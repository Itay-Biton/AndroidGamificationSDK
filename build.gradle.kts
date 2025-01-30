// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.maven.publish) // Apply the maven-publish plugin
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.ItayBiton" 
                artifactId = "GamificationLib"
                version = "1.0.0"
                artifact(tasks.getByName("bundleReleaseAar"))

                // Add dependencies to the Maven publication configuration (api or implementation) 
            }
        }
    }
}