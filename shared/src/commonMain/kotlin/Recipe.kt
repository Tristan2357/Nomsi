import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
@Serializable
data class Recipe(
  val id: String = uuid4().toString(),
  val title: String,
  val ingredients: Array<Ingredient>,
  val steps: Array<Step>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Recipe) return false

    if (id != other.id) return false
    if (title != other.title) return false
    if (!ingredients.contentEquals(other.ingredients)) return false
    if (!steps.contentEquals(other.steps)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + title.hashCode()
    result = 31 * result + ingredients.contentHashCode()
    result = 31 * result + steps.contentHashCode()
    return result
  }

  companion object {
    const val path = "/recipe"
  }
}
