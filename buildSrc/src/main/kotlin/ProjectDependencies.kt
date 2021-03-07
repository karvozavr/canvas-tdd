object LibVers {
    const val junit = "5.7.1"
    const val arrow = "0.11.0"
    const val kotest = "4.4.1"
}

object Libs {
    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Global.kotlin_version}"
    const val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Global.kotlin_version}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Global.kotlin_version}"

    // arrow
    const val arrow = "io.arrow-kt:arrow-core:${LibVers.arrow}"

    // Tests
    const val junit_params = "org.junit.jupiter:junit-jupiter-params:${LibVers.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${LibVers.junit}"
    const val kotest_junit = "io.kotest:kotest-runner-junit5:${LibVers.kotest}"
    const val kotest_arrow = "io.kotest:kotest-assertions-arrow:${LibVers.kotest}"
}

object PluginVers {
    const val kotlin = Global.kotlin_version
    const val detekt = "1.15.0"
    const val detekt_formatting = detekt
}

object Plugins {
    const val jacoco = "jacoco"
    const val kotlin = "org.jetbrains.kotlin.jvm"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting"
}