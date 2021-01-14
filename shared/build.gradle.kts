plugins {
    kotlin("multiplatform") version "1.6.10"
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
