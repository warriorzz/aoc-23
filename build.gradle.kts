plugins {
    kotlin("jvm") version "1.9.21"
    application
}

repositories {
    mavenCentral()
}

group = "com.github.warriorzz.aoc"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.github.warriorzz.aoc.LauncherKt")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
