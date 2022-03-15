package de.nomsi.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Recipe(
  val title: String,
  val ingredients: Collection<Ingredient>,
  val steps: List<Step>,
  val id: String = UUID.randomUUID().toString()
) {

  companion object {
    const val path = "/recipe"
  }
}
