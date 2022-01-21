package models

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport


@ExperimentalJsExport
@JsExport
data class Recipe(val title: String, val ingredients: Collection<Ingredient>, val steps: List<Step>)
