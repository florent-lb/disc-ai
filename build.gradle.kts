plugins {
    java
    id("io.quarkus")
    id("io.freefair.lombok") version "8.6"
    kotlin("jvm")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val jdaVersion = "5.0.0-beta.24"

dependencies {
    //MapStruck
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    implementation("io.quarkus:quarkus-liquibase")
    //DATABASE
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-h2")

    implementation("io.quarkus:quarkus-rest-jsonb")

    implementation("io.quarkiverse.langchain4j:quarkus-langchain4j-openai:0.14.1")
    implementation("io.quarkiverse.langchain4j:quarkus-langchain4j-core:0.14.1")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")

    implementation("net.dv8tion:JDA:$jdaVersion")
}

group = "dev.flb"
version = "1.0.0-SNAPSHOT"

java {
}

val preparePropertiesTemplate by tasks.registering(Copy::class) {

    var templateName = "application-template.properties"
    val destDir = "src/main/resources"
    val finalFileName = "application.properties"
    from("src/main/resources/${templateName}")
    rename(templateName, finalFileName)
    println("Copy template $templateName to $destDir as $finalFileName")
    into(destDir)
    expand(project.properties)
}

tasks.getByName("processResources").dependsOn(preparePropertiesTemplate)
tasks.getByName("processResources").mustRunAfter(preparePropertiesTemplate)