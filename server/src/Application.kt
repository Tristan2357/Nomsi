package de.nomsi

import de.nomsi.routing.recipeApi
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import models.Ingredient
import models.Recipe
import models.Step
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import kotlin.js.ExperimentalJsExport
import kotlin.random.Random


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@ExperimentalJsExport
@Suppress("unused") // Referenced in application.conf
fun Application.module() {

  install(AutoHeadResponse)

  install(CallLogging)

  install(CORS) {
    method(HttpMethod.Options)
    method(HttpMethod.Get)
    method(HttpMethod.Post)

    allowNonSimpleContentTypes = true
    // allowCredentials = true

    anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
  }

  install(CachingHeaders) {
    val noCache = CachingOptions(CacheControl.NoCache(CacheControl.Visibility.Public))
    val lazy = CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60 * 10)) // 10 minutes
    val superLazy = CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60 * 60)) // 1 hour
    options { outgoingContent ->
      when (outgoingContent.contentType?.withoutParameters()) {
        ContentType.Text.Html -> noCache
        ContentType.Text.CSS -> lazy
        ContentType.Text.JavaScript -> lazy

        ContentType.Image.SVG -> superLazy
        ContentType.Image.JPEG -> superLazy
        ContentType.Image.PNG -> superLazy
        ContentType("image", "fav") -> superLazy
        else -> null
      }
    }
  }

  install(ContentNegotiation) {
    json()
  }

  routing {
    when {
      isDev -> {
        // redirect to Vue dev server
        get("/") {
          call.respondRedirect("http://localhost:8080")
        }
      }
      isProd -> {
        // serve Vue page with Ktor
        static("/") {
          resources("dist")
          resource("/", "dist/index.html")
        }
      }
    }

    // serve assets
    static("assets") {
      resources("assets")
    }

    // actual API code
    route("/api") {
      recipeApi()
    }
  }
}

val Application.envKind get() = environment.config.propertyOrNull("ktor.environment")?.getString()
val Application.isDev get() = envKind != null && envKind == "dev"
val Application.isProd get() = envKind != null && envKind != "dev"

val client = KMongo.createClient().coroutine

fun populateWithRecipes(amount: Int): MutableList<Recipe> {
  val recipes: MutableList<Recipe> = ArrayList()
  val ingredientList = listOf(
    Ingredient("Tofu", 2),
    Ingredient("Nudeln", 500),
    Ingredient("Erdnussmus", 30),
    Ingredient("Apfel", 3),
    Ingredient("Brot", 300),
    Ingredient("Kürbis", 1000)
  )
  val stepList = listOf<Step>(
    Step("Tofu kochen und davor schneiden vlt"),
    Step("Nudeln kochen und davor schneiden vlt"),
    Step("Erdnussmus kochen und davor schneiden vlt"),
    Step("Apfel kochen und davor schneiden vlt"),
    Step("Brot kochen und davor schneiden vlt"),
    Step("Kürbis kochen und davor schneiden vlt"),
    Step("alles vermengen lol")
  )
  val randomIngredients = ingredientList.asSequence().shuffled().take(Random.nextInt(5)).toList()
  val randomSteps = stepList.asSequence().shuffled().take(Random.nextInt(5)).toList()
  for (i in 0..amount) {
    recipes.add(Recipe("testRecipe Nr${i} with a really really really long long name", randomIngredients, randomSteps))
  }
  return recipes
}
