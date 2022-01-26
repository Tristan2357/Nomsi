val kmongoVersion: String by project
val serializationVersion: String by project

plugins {
  kotlin("multiplatform") version "1.6.10"
  kotlin("plugin.serialization") version "1.6.10"
  id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

group = "de.nomsi"
version = "0.0.1"

repositories {
  jcenter()
  mavenCentral()
}

kotlin {
  js(IR) {
    browser {
      binaries.executable()
    }
  }
  jvm()
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
        implementation("com.benasher44:uuid:0.4.0")
      }
    }
  }
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

tasks {
  "build" {
    doLast {
      copy {
        from("$buildDir/processedResources/js/main")
        include("package.json")
        into("$buildDir/libs/shared-types/")
      }
      copy {
        from("$buildDir/compileSync/main/productionExecutable/kotlin")
        into("$buildDir/libs/shared-types/")
        rename { name -> name.replace("${rootProject.name}-shared", "index") }
      }
    }
    finalizedBy(":client:addPackage")
  }
}

tasks.getByPath("detekt").onlyIf {
  gradle.startParameter.taskNames.contains("detekt") || gradle.startParameter.taskNames.contains("check")
}
