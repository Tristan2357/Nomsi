package models

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@Serializable
@ExperimentalJsExport
@JsExport
data class Ingredient (val name: String, val amount: Int)
