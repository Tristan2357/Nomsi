package de.nomsi.routing

import de.nomsi.client
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Recipe
import org.litote.kmongo.eq

val database = client.getDatabase("recipe")
val collection = database.getCollection<Recipe>()

fun Route.recipeApi() {
  route(Recipe.path) {
    get {
      call.respond(collection.find().toList())
    }
    get("/{id}") {
      val id = call.parameters["id"] ?: error("Invalid get request")
      val recipe: Recipe = collection.findOne(Recipe::id eq id)!!
      call.respond(recipe)
    }
    post {
      collection.insertOne(call.receive<Recipe>())
      call.respond(HttpStatusCode.OK)
    }
    delete("/{id}") {
      val id = call.parameters["id"] ?: error("Invalid delete request")
      collection.deleteOne(Recipe::id eq id)
      call.respond(HttpStatusCode.OK)
    }
  }
}
