package models

import com.benasher44.uuid.UuidHasher
import com.benasher44.uuid.uuid4
import com.benasher44.uuid.uuidFrom
import com.benasher44.uuid.uuidOf
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
  val id: String = uuid4().toString()
) {
  companion object {
    const val path = "/recipe"
  }
}
