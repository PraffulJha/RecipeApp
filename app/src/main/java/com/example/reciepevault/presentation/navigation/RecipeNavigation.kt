package com.example.reciepevault.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "recipe_search"
    ) {
        composable("recipe_search") {
            RecipeSearchScreen(
                onNavigateToDetail = { recipeId ->
                    navController.navigate("recipe_detail/$recipeId")
                }
            )
        }
        composable("recipe_detail/{recipeId}") {
            RecipeDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}