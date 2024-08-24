import jetbrains.buildServer.configs.kotlin.v2022_2.*

version = "2022.2"

project {
    // Define the VCS root for your project
    vcsRoot(HttpsGithubComAkashParalikarDevOpsSampleJavaGradleGitRefsHeadsMaster)

    // Define the build configuration
    buildType(Build)
}

// Define the build configuration
object Build : BuildType({
    name = "Build and Push Docker Image"

    vcs {
        root(HttpsGithubComAkashParalikarDevOpsSampleJavaGradleGitRefsHeadsMaster)
    }

    steps {
        gradle {
            name = "Build with Gradle"
            tasks = "jib"
            gradleWrapperPath = "gradlew" // Adjust if your wrapper script is in a different location
        }
    }

    triggers {
        vcs {
            // Trigger build on every VCS check-in
        }
    }

    features {
        perfmon {
            // Optional: Configure performance monitoring if needed
        }
    }
})
