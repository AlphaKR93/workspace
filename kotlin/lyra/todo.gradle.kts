plugins {
    `maven-publish`
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"

    id("org.jetbrains.dokka") version "2.0.0"
    id("org.jetbrains.dokka-javadoc") version "2.0.0"
}

kotlin.jvmToolchain(21)
publishing.publications.create<MavenPublication>("maven") { from(components["java"]) }

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("metadata-jvm"))

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.8")

    dokkaHtmlPlugin("org.jetbrains.dokka:versioning-plugin")
}

dokka {
    moduleName.set("dokdo")
    dokkaSourceSets.main {
        sourceLink {
            remoteUrl("https://github.com/AlphaKR93/dokdo")
        }
    }
    pluginsConfiguration.html {
        footerMessage.set("&copy; 2024-2025 AlphaKR93")
    }
}

tasks {
    val sourcesJar by registering(Jar::class) {
        from(sourceSets.main.get().allSource)
        archiveClassifier.set("sources")
    }

    val javadocJar by registering(Jar::class) {
        dependsOn(dokkaGeneratePublicationJavadoc)
        from(dokkaGeneratePublicationJavadoc.flatMap { it.outputDirectory })
        archiveClassifier.set("javadoc")
    }

    build {
        dependsOn(sourcesJar, javadocJar, dokkaGeneratePublicationHtml)
    }

    artifacts {
        archives(jar)
        archives(sourcesJar)
        archives(javadocJar)
    }
}
