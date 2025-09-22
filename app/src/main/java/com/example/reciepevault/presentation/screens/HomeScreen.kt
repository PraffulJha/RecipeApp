package com.example.reciepevault.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reciepevault.data.models.Recipe
import com.example.reciepevault.presentation.components.AllRecipeCard
import com.example.reciepevault.presentation.components.PopularRecipeCard
import com.example.reciepevault.presentation.navigation.BottomNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userName: String,
    recipes: List<Recipe>,
    isLoading: Boolean,
    onSearch: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

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
                        text = "Home-Dashboard",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        Text(
                            text = "👋",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Column {
                            Text(
                                text = "Hey $userName",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Discover tasty and healthy recipe!",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    // Search Bar
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            onSearch(it)
                        },
                        placeholder = { Text("Search Any Recipe") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF6B35),
                            unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                        )
                    )
                }
            }

            item {
                // Popular Recipes Section
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Popular Recipes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color(0xFFFF6B35)
                        )
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(recipes.take(3)) { recipe ->
                                PopularRecipeCard(recipe = recipe)
                            }
                        }
                    }
                }
            }

            item {
                // All Recipes Section
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "All Recipes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            if (!isLoading) {
                items(recipes.drop(3)) { recipe ->
                    AllRecipeCard(
                        recipe = recipe,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Bottom Navigation
        BottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
        )
    }
}





