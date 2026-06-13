plugins {
    id("java")
    application
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("org.slf4j:slf4j-api:2.0.13")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes(mapOf("Main-Class" to "org.example.Main"))
    }
}

abstract class PrintInfoTask : DefaultTask() {
    @TaskAction
    fun print() {
        println("первая задача")
        println("Проект: ${project.name}")
        println("Версия Gradle: ${project.gradle.gradleVersion}")
    }
}

tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Выводит информацию о проекте"
}

abstract class GenerateBuildInfoTask : DefaultTask() {
    @TaskAction
    fun generate() {
        val outputDir = project.layout.projectDirectory.dir("src/main/resources").asFile
        outputDir.mkdirs()
        val file = outputDir.resolve("build-passport.properties")
        val user = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        val os = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        val date = System.currentTimeMillis().toString()
        file.writeText(
            "build.user=$user\n" +
                    "build.os=$os\n" +
                    "build.java=$javaVersion\n" +
                    "build.date=$date\n" +
                    "build.message=Hello from Gradle build passport!\n"
        )
        println("build-passport.properties сгенерирован")
    }
}

tasks.register<GenerateBuildInfoTask>("generateBuildInfo") {
    group = "Custom"
    description = "Генерирует файл с информацией о сборке"
}

tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildInfo"))
}