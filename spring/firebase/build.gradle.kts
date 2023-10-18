plugins {
	java
	id("org.springframework.boot") version "2.7.16"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("com.google.firebase:firebase-admin:9.1.1")
    testImplementation("junit:junit:4.13.1")

    compileOnly ("org.projectlombok:lombok")
	annotationProcessor ("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
