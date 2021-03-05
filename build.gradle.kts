plugins {
    id(Plugins.kotlin) version PluginVers.kotlin apply false
}

subprojects {
    apply {
        plugin("java")
        plugin(Plugins.kotlin)
    }

    repositories {
        jcenter()
        mavenCentral()
        mavenLocal()
    }
}