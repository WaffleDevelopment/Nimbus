import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    "com.github.johnrengelman.shadow"
}

group = "com.panav"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    jcenter()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.5")
    implementation("io.github.cdimascio:dotenv-java:2.3.2")
    implementation ("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application{
    mainClass.set("com.panav.nimbus.NimbusKt")
}