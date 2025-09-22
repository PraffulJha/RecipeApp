package com.example.reciepevault.data.models

data class RecipeSearchResponse(
    val results: List<Recipe>,
    val totalResults: Int,
    val offset: Int,
    val number: Int
)