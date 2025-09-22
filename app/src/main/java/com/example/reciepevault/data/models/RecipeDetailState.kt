package com.example.reciepevault.data.models

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val error: String = ""
)