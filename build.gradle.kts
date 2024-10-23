plugins {
    kotlin("jvm") version "2.0.21"
    id("fabric-loom") version "1.8-SNAPSHOT"
    java
}

group = "ru.cororo.stackcrafter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings("net.fabricmc:yarn:1.20.1+build.10")
    modImplementation("net.fabricmc:fabric-loader:0.16.7")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.12.3+kotlin.2.0.21")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+1.20.1")
}

kotlin {
    jvmToolchain(21)
}

java {
    withSourcesJar()
}
