package models

import com.benasher44.uuid.uuidFrom
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
@Serializable
data class Recipe(
  val title: String,
  val ingredients: Collection<Ingredient>,
  val steps: List<Step>,
  val id: String = uuidFrom(title).toString()
) {

  companion object {
    const val path = "/recipe"
  }
}
