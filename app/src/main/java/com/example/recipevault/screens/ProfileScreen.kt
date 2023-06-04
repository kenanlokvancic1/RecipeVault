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
import com.example.recipevault.ui.theme.BlueViolet1

@Composable
fun ProfileScreen() {

        Column(modifier = Modifier.fillMaxSize()
            .background(BlueViolet1)
            .padding(28.dp)
            .fillMaxSize()
            .background(BlueViolet1)) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource(id = R.drawable.profile),
            )
            Spacer(modifier = Modifier.height(10.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message)
            )
            Spacer(modifier = Modifier.height(10.dp))

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.lock)
            )

            Spacer(modifier = Modifier.height(70.dp))



        }
}