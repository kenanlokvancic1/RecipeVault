package com.example.recipevault.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.recipevault.database.AppDatabase
import com.example.recipevault.database.User
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(){
    val state = remember { mutableStateOf(User(0, "", "", "")) }
    val context = LocalContext.current

        Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

        ){
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value =  stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.username),
                    painterResource(id = R.drawable.profile)
                ) { username ->
                    state.value = state.value.copy(
                        username = username
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message)
                ) { email ->
                    state.value = state.value.copy(
                        email = email
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.lock)
                ) { password ->
                    state.value = state.value.copy(
                        password = password
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.register)
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        registerUser(
                            context = context,
                            user = state.value
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
                DividerTextComponent()
                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    RecipeVaultAppRouter.navigateTo(Screen.LoginScreen)
                })

                
            }


        }

}



@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}

private suspend fun registerUser(context: Context, user: User) {
   val database = AppDatabase.getInstance(context)
    database.userDao().insertUser(user)
    RecipeVaultAppRouter.navigateTo(Screen.HomeScreen)
}



