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
    implementation("org.springframework:spring-context:6.0.11")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("Hw05-builder-iterator-patterns")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.java.pro.homework10.Application"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}