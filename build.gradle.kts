val bundle = tasks.register("bundle")
tasks {
  "bundle" {
    finalizedBy(":client:build", ":server:build")
  }
}
