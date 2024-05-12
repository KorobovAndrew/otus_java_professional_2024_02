import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id ("java")
    id ("com.github.johnrengelman.shadow")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("Hw05-builder-iterator-patterns")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.java.pro.homework05.Program"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}