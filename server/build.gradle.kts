val ktorVersion: String by project
val kotlinVersion: String by project
val serializationVersion: String by project
val kmongoVersion: String by project
val logbackVersion: String by project

plugins {
  application
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.serialization") version "1.6.10"
  id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

group = "de.nomsi"
version = "0.0.1"

application {
  @Suppress("DEPRECATION")
  mainClassName = "io.ktor.server.netty.EngineMain"
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
  mavenLocal()
  jcenter()
  maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-host-common:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")
  implementation("org.litote.kmongo:kmongo-id-serialization:$kmongoVersion")
  testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("resources")

val fatJar = task("fatJar", type = org.gradle.jvm.tasks.Jar::class) {
  duplicatesStrategy = DuplicatesStrategy.INCLUDE

  manifest {
    attributes["Implementation-Title"] = "Nomsi Fat Jar"
    attributes["Implementation-Version"] = project.version
    attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
  }
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
  with(tasks.jar.get() as CopySpec)
}

var env = "production"

val setDev = tasks.register("setDev") {
  env = "development"
}

detekt {
  source = files("src", "test")
  parallel = true
  config = files("../detekt.yml")
  buildUponDefaultConfig = false
  allRules = true
  baseline = file("baseline.xml")
  debug = true
  basePath = "./"
  // If set to `true` the build does not fail when the
  // maxIssues count was reached. Defaults to `false`.
  ignoreFailures = false

  ignoredBuildTypes = listOf("release")
  ignoredFlavors = listOf("production")
  ignoredVariants = listOf("productionRelease")
}

tasks.processResources {
  outputs.upToDateWhen { false }
  filesMatching("*.conf") {
    when (env) {
      "development" -> {
        expand(
          "KTOR_ENV" to "dev",
          "KTOR_PORT" to "8081",
          "KTOR_MODULE" to "build",
          "KTOR_AUTORELOAD" to "true"
        )
      }
      "production" -> {
        expand(
          "KTOR_ENV" to "production",
          "KTOR_PORT" to "80",
          "KTOR_MODULE" to "",
          "KTOR_AUTORELOAD" to "false"
        )
      }
    }
  }
}

tasks {
  "run" {
    dependsOn(setDev)
  }
  "build" {
    dependsOn(":client:build")
    dependsOn(fatJar)
    doLast {
      copy {
        delete("$rootDir/build")
        from(fatJar)
        into(file("$rootDir/build"))
      }
    }
  }
}

tasks.getByPath("detekt").onlyIf {
  gradle.startParameter.taskNames.contains("detekt") || gradle.startParameter.taskNames.contains("check")
}
