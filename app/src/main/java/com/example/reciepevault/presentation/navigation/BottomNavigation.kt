package com.example.reciepevault.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reciepevault.data.models.Screen

@Composable
fun BottomNavigation(
    currentScreen: Screen,
    onNavigateToHome: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.White)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onNavigateToHome() }
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home",
                tint = if (currentScreen == Screen.HOME) Color(0xFFFF6B35) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Home",
                fontSize = 12.sp,
                color = if (currentScreen == Screen.HOME) Color(0xFFFF6B35) else Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onNavigateToFavorites() }
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favourites",
                tint = if (currentScreen == Screen.FAVORITES) Color(0xFFFF6B35) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Favourite",
                fontSize = 12.sp,
                color = if (currentScreen == Screen.FAVORITES) Color(0xFFFF6B35) else Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}