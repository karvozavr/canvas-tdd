plugins {
    application
}

group = "com.github.karvozavr.canvas"

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

    implementation(project(":canvas"))
}

application {
    applicationName = "canvas-app"
    mainClassName = "MainKt"
}

tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}