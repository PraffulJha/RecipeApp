package com.example.reciepevault.data.models

data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val imageType: String?,
    val servings: Int?,
    val readyInMinutes: Int?,
    val pricePerServing: Double?,
    val healthScore: Double?,
    val spoonacularScore: Double?,
    val summary: String?,
    val cuisines: List<String> = emptyList(),
    val dishTypes: List<String> = emptyList(),
    val diets: List<String> = emptyList(),
    val instructions: String?,
    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    val nutrition: Nutrition?,
    val cheap: Boolean = false,
    val dairyFree: Boolean = false,
    val glutenFree: Boolean = false,
    val ketogenic: Boolean = false,
    val vegan: Boolean = false,
    val vegetarian: Boolean = false,
    val veryHealthy: Boolean = false,
    val veryPopular: Boolean = false
)