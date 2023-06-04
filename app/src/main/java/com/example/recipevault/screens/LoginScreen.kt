package com.example.recipevault.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipevault.R
import com.example.recipevault.components.ButtonComponent
import com.example.recipevault.components.ClickableLoginTextComponent
import com.example.recipevault.components.DividerTextComponent
import com.example.recipevault.components.HeadingTextComponent
import com.example.recipevault.components.MyTextFieldComponent
import com.example.recipevault.components.NormalTextComponent
import com.example.recipevault.components.PasswordTextFieldComponent
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import com.example.recipevault.navigation.SystemBackButtonHandler


@Composable
fun LoginScreen(){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ){
        Column(Modifier
            .fillMaxSize()){

            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(60.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource(R.drawable.profile)
            )
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.lock)
            )
            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.register)) {
                RecipeVaultAppRouter.navigateTo(Screen.HomeScreen)
            }
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                RecipeVaultAppRouter.navigateTo(Screen.SignUpScreen)
            })
        }



    }

    SystemBackButtonHandler {
        RecipeVaultAppRouter.navigateTo(Screen.SignUpScreen)
    }

}



@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}
