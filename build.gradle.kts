plugins {
    kotlin("jvm") version "2.4.0"
}

group = "org.discordfy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        url = uri("https://maven.lavalink.dev/snapshots")
    }

}

dependencies {

    implementation("net.dv8tion:JDA:6.3.1")
    implementation("dev.arbjerg:lavaplayer:2.2.7")
    implementation("moe.kyokobot.libdave:adapter-jda:latest.release")
    implementation("moe.kyokobot.libdave:impl-jni:latest.release")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}