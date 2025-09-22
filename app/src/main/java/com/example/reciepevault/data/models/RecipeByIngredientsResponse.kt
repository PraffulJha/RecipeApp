package com.example.reciepevault.data.models

data class RecipeByIngredientsResponse(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<Ingredient>,
    val usedIngredients: List<Ingredient>,
    val unusedIngredients: List<Ingredient>,
    val likes: Int
)