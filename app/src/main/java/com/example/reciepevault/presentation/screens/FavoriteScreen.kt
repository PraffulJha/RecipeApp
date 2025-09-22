package com.example.reciepevault.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reciepevault.data.models.Recipe
import com.example.reciepevault.data.models.Screen
import com.example.reciepevault.presentation.navigation.BottomNavigation

@Composable
fun FavouriteScreen(
    favoriteRecipes: List<Recipe>,
    onNavigateToHome: () -> Unit = {},
    onToggleFavorite: (Recipe) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(bottom = 80.dp)
        ) {
            item {
                // Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Favourite",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Favourite recipes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }
            }

            if (favoriteRecipes.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "No favorites",
                                modifier = Modifier.size(64.dp),
                                tint = Color.Gray.copy(alpha = 0.5f)
                            )
                            Text(
                                text = "No favourite recipes yet",
                                fontSize = 18.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Text(
                                text = "Start adding recipes to your favourites!",
                                fontSize = 14.sp,
                                color = Color.Gray.copy(alpha = 0.7f),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            } else {
                items(favoriteRecipes) { recipe ->
                    FavoriteRecipeCard(
                        recipe = recipe,
                        onToggleFavorite = { onToggleFavorite(recipe) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Bottom Navigation
        BottomNavigation(
            currentScreen = Screen.FAVORITES,
            onNavigateToHome = onNavigateToHome,
            onNavigateToFavorites = { },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
        )
    }
}