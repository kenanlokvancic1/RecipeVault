package com.example.recipevault.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipevault.R
import com.example.recipevault.components.ButtonComponent
import com.example.recipevault.components.HeadingTextComponent
import com.example.recipevault.components.HomeButtons
import com.example.recipevault.components.MainTextComponent
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import com.example.recipevault.ui.theme.AccentColor
import com.example.recipevault.ui.theme.BlueViolet1
import com.example.recipevault.ui.theme.ButtonBlue
import com.example.recipevault.ui.theme.LightGreen1
import com.example.recipevault.ui.theme.LightRed
import com.example.recipevault.ui.theme.Primary
import com.example.recipevault.ui.theme.Secondary

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .background(BlueViolet1)
            .padding(28.dp)
            .fillMaxSize()
            .background(BlueViolet1)

    ) {
        Spacer(modifier = Modifier.height(5.dp))
        MainTextComponent(value = stringResource(id = R.string.recipevault))
        Spacer(modifier = Modifier.height(190.dp))
        HomeButtons(value = stringResource(id = R.string.recipes)) {
            RecipeVaultAppRouter.navigateTo(Screen.RecipeScreen)
        }
        Spacer(modifier = Modifier.height(20.dp))
        HomeButtons(value = stringResource(id = R.string.shopping_cart)) {
            RecipeVaultAppRouter.navigateTo(Screen.ShoppingCartScreen)
        }
        Spacer(modifier = Modifier.height(20.dp))
        HomeButtons(value = stringResource(id = R.string.timer)) {
            RecipeVaultAppRouter.navigateTo(Screen.TimerScreen)
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}





@Preview
@Composable
fun DefaultPreviewOfHomeScreen(){
    HomeScreen()
}


