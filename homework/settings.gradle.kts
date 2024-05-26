rootProject.name = "homework"

include("hw02-annotations")
include("hw03-stream-api")
include("hw04-multithreading")
include("hw05-builder-iterator-patterns")
include("hw06-proxy-pattern")

pluginManagement {
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
    }
}