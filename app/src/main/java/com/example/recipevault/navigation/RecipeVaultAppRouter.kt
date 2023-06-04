package com.example.recipevault.navigation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object ShoppingCartScreen : Screen()
    object RecipeScreen : Screen()
    object TimerScreen: Screen()
}


object RecipeVaultAppRouter{

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

}