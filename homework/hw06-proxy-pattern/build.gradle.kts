import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id ("java")
    id ("com.github.johnrengelman.shadow")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    implementation("com.h2database:h2:1.2.121")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("Hw06-proxy-pattern")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.java.pro.homework06.Program"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}