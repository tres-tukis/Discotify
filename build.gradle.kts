plugins {
    kotlin("jvm") version "2.4.0"
}

group = "org.discordfy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("net.dv8tion:JDA:5.6.1")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}