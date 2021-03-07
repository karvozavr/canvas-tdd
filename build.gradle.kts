val rootProjectDir = projectDir

plugins {
    id(Plugins.kotlin) version PluginVers.kotlin apply false
    id(Plugins.detekt) version PluginVers.detekt
}

subprojects {

    apply {
        plugin("java")
        plugin(Plugins.kotlin)
        plugin(Plugins.detekt)
        plugin(Plugins.jacoco)
    }

    repositories {
        jcenter()
        mavenCentral()
        mavenLocal()
    }

    detekt {
        config = files("$rootProjectDir/detekt/detekt-config.yml")
        buildUponDefaultConfig = true
        input = files("src/main/kotlin", "src/test/kotlin")

        reports {
            html.enabled = true
        }

        dependencies {
            detektPlugins("${Plugins.detekt_formatting}:${PluginVers.detekt_formatting}")
        }
    }

    tasks {
        val check = named<DefaultTask>("check")

        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

        check {
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(check)
            finalizedBy(jacocoTestCoverageVerification)
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {

                rule {
                    excludes = listOf("application", "telnet")
                    limit {
                        minimum = BigDecimal("0.94")
                    }
                }
            }
        }

        val failOnWarning = project.properties["allWarningsAsErrors"] != null &&
                project.properties["allWarningsAsErrors"] == "true"

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                allWarningsAsErrors = failOnWarning
                freeCompilerArgs = listOf("-Xjvm-default=enable")
            }
        }

        withType<Test> {
            useJUnitPlatform()

            // enable verbose logging
            testLogging {
                events(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }
        }
    }
}