package com.example.reciepevault.presentation.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciepevault.data.models.Recipe
import com.example.reciepevault.data.models.RecipeUiState
import com.example.reciepevault.data.models.Screen
import com.example.reciepevault.data.repository.RecipeRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    private lateinit var googleSignInClient: GoogleSignInClient

    fun signInWithGoogle(activity: ComponentActivity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)

        val signInIntent = googleSignInClient.signInIntent
        // Handle with ActivityResultLauncher in your activity
        simulateGoogleSignIn()
    }

    private fun simulateGoogleSignIn() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isSignedIn = true,
                userName = "John", // Replace with actual Google user name
                isLoading = true
            )
            loadRecipes()
        }
    }

    fun loadRecipes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            repository.getPopularRecipes()
                .onSuccess { recipes ->
                    _uiState.value = _uiState.value.copy(
                        recipes = recipes,
                        isLoading = false
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        error = error.message,
                        isLoading = false,
                        recipes = getFallbackRecipes() // Show fallback data
                    )
                }
        }
    }


    fun navigateToScreen(screen: Screen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    private fun getFallbackRecipes(): List<Recipe> {
        return listOf(
            Recipe(
                1,
                "Shahi Paneer",
                "https://spoonacular.com/recipeImages/1-556x370.jpg",
                "25",
                4,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            ),
            Recipe(
                2,
                "Perfect Fried Egg",
                "https://spoonacular.com/recipeImages/2-556x370.jpg",
                "5",
                1,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            ),
            Recipe(
                3,
                "Chicken Curry",
                "https://spoonacular.com/recipeImages/3-556x370.jpg",
                "45",
                4,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            ),
            Recipe(
                4,
                "Pasta Primavera",
                "https://spoonacular.com/recipeImages/4-556x370.jpg",
                "30",
                4,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            ),
            Recipe(
                5,
                "Grilled Salmon",
                "https://spoonacular.com/recipeImages/5-556x370.jpg",
                "20",
                2,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            ),
            Recipe(
                6,
                "Vegetable Stir Fry",
                "https://spoonacular.com/recipeImages/6-556x370.jpg",
                "15",
                3,
                null,
                pricePerServing = null,
                healthScore = null,
                spoonacularScore = null,
                summary = null,
                instructions = null,
                nutrition = null,
            )
        )
    }
}

