package de.nomsi

import de.nomsi.routing.recipeApi
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import kotlin.js.ExperimentalJsExport


fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

private const val TEN_MINUTES = 600

private const val ONE_HOUR = 3600

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

    anyHost()
  }

  install(CachingHeaders) {
    val noCache = CachingOptions(CacheControl.NoCache(CacheControl.Visibility.Public))
    val lazy = CachingOptions(CacheControl.MaxAge(maxAgeSeconds = TEN_MINUTES))
    val superLazy = CachingOptions(CacheControl.MaxAge(maxAgeSeconds = ONE_HOUR))
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
