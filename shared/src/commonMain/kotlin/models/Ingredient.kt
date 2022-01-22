package models

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient (val name: String, val amount: Int)
