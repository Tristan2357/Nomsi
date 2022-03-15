package de.nomsi.models

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(val name: String, val amount: Int, val unit: String)
