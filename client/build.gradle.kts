plugins {
    id("org.siouan.frontend-jdk8") version "4.0.1"
}

apply(plugin = "org.siouan.frontend-jdk8")

frontend {
    yarnEnabled.set(true) // you might change this to false, when using npm
    yarnDistributionProvided.set(true) // you might change this to false, when yarn not installed globally
    nodeDistributionProvided.set(true) // you might change this to false, when node not installed globally

    installScript.set("install")
    assembleScript.set("build")
}

tasks {
    "build" {
        doLast {
            copy {
                from("$projectDir/dist/")
                into("../server/resources/dist/")
            }
        }
    }
}
