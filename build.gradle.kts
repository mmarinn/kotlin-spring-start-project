import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.kotest") version "0.2.6"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.avast.gradle.docker-compose") version "0.7.1"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
    application

 }

group = "com.kotlinspring"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    testImplementation("io.kotest:kotest-runner-junit5:4.3.0")
    testImplementation("io.kotest:kotest-extensions-spring:4.3.0")
    testImplementation("io.kotest:kotest-assertions-json:4.4.1")

    testImplementation("io.mockk:mockk:1.10.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:1.15.2")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.4.0")
    testImplementation("org.testcontainers:postgresql:1.15.2")

}

dockerCompose {
    createNested("myNested").apply {
        useComposeFiles = listOf("src/main/docker/docker-compose.yml")
        isRequiredBy(project.tasks.named("bootRun").get())
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "com.kotlinspring.ApplicationKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}