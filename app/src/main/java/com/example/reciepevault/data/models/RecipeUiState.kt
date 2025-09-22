package com.example.reciepevault.data.models

data class RecipeUiState(
    val isSignedIn: Boolean = false,
    val userName: String = "",
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentScreen: Screen = Screen.HOME
)