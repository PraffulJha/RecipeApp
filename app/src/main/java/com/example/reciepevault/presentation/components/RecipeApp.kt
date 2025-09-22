package com.example.reciepevault.presentation.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reciepevault.presentation.screens.HomeScreen
import com.example.reciepevault.presentation.screens.WelcomeScreen
import com.example.reciepevault.presentation.viewmodel.RecipeSearchViewModel
import com.example.reciepevault.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeApp(viewModel: RecipeViewModel = viewModel(),recipeSearchViewModel: RecipeSearchViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when {
        !uiState.isSignedIn -> {
            WelcomeScreen(
                onGoogleSignIn = {
                    viewModel.signInWithGoogle(context as ComponentActivity)
                }
            )
        }
        uiState.currentScreen == Screen.HOME -> {
            HomeScreen(
                userName = uiState.userName,
                recipes = uiState.recipes,
                isLoading = uiState.isLoading,
                onSearch = { query -> recipeSearchViewModel.searchRecipes(query) },
                onNavigateToFavorites = { viewModel.navigateToScreen(Screen.FAVORITES) },
                onToggleFavorite = { recipe -> viewModel.toggleFavorite(recipe) },
                favoriteRecipes = uiState.favoriteRecipes
            )
        }
        uiState.currentScreen == Screen.FAVORITES -> {
            FavouriteScreen(
                favoriteRecipes = uiState.favoriteRecipes,
                onNavigateToHome = { viewModel.navigateToScreen(Screen.HOME) },
                onToggleFavorite = { recipe -> viewModel.toggleFavorite(recipe) }
            )
        }
    }
}
