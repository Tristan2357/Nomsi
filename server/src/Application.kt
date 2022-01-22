package de.nomsi

import de.nomsi.routing.recipeApi
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import models.User
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.*
import kotlin.js.ExperimentalJsExport

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
