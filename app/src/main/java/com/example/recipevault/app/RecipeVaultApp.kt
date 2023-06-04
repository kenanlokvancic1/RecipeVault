package com.example.recipevault.app


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.recipevault.navigation.Screen
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.screens.SignUpScreen
import com.example.recipevault.screens.LoginScreen
import com.example.recipevault.screens.HomeScreen
import com.example.recipevault.screens.ShoppingCartScreen
import com.example.recipevault.screens.RecipeScreen
import com.example.recipevault.screens.TimerScreen

@Composable
fun RecipeVaultApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = RecipeVaultAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
                is Screen.ShoppingCartScreen -> {
                    ShoppingCartScreen()
                }
                is Screen.RecipeScreen -> {
                    RecipeScreen()
                }
                is Screen.TimerScreen -> {
                    TimerScreen()
                }

            }

        }
    }

}