import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
}

group = "com.github.karvozavr.canvas"
version = "1.0-SNAPSHOT"

dependencies {
    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // arrow
    implementation(Libs.arrow)

    // test
    testImplementation(Libs.kotest_junit)
    testImplementation(Libs.kotest_arrow)
    testImplementation(Libs.junit_engine)
    testImplementation(Libs.junit_params)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}