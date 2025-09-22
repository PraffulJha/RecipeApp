package com.example.reciepevault.data.models

data class RecipeSearchState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = "",
    val searchQuery: String = "",
    val totalResults: Int = 0,
    val hasMorePages: Boolean = true,
    val currentOffset: Int = 0
)