import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
    java
}

repositories {
    mavenCentral()
    maven("https://repository.ow2.org/nexus/content/repositories/releases")
}

dependencies {
    implementation("org.ow2.asm:asm-all:5.2")
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        mergeServiceFiles()
        minimize()
    }
    test { useJUnitPlatform() }
    build { dependsOn(shadowJar) }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.properties["group"] as? String?
            artifactId = project.name
            version = project.properties["version"] as? String?

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "HyperaReleases"
            url = uri("https://repo.hypera.dev/releases/")
            credentials {
                username = System.getenv("ORG_GRADLE_PROJECT_hyperaReleasesUsername")
                password = System.getenv("ORG_GRADLE_PROJECT_hyperaReleasesPassword")
            }
        }
        maven {
            name = "HyperaSnapshots"
            url = uri("https://repo.hypera.dev/snapshots/")
            credentials {
                username = System.getenv("ORG_GRADLE_PROJECT_hyperaSnapshotsUsername")
                password = System.getenv("ORG_GRADLE_PROJECT_hyperaSnapshotsPassword")
            }
        }
    }
}