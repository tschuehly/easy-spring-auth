plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "de.tschuehly"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    // LAB 1:
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("gg.jte:jte-spring-boot-starter-3:3.1.12")
    implementation("gg.jte:jte:3.1.12")

    implementation("de.tschuehly:spring-view-component-jte:0.7.5-SNAPSHOT")
    annotationProcessor("de.tschuehly:spring-view-component-core:0.7.5-SNAPSHOT")
    implementation("io.github.wimdeblauwe:htmx-spring-boot:3.3.0")

    // LAB 9:
    implementation("io.projectreactor:reactor-core:3.6.5")

    implementation("net.datafaker:datafaker:2.1.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
