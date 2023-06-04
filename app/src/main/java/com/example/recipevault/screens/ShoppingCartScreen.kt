package com.example.recipevault.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipevault.database.AppDatabase
import com.example.recipevault.database.Cart
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import com.example.recipevault.navigation.SystemBackButtonHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen() {
    var name by remember { mutableStateOf("")}
    val names = remember {
        mutableStateListOf<String>()
    }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            SystemBackButtonHandler {
                RecipeVaultAppRouter.navigateTo(Screen.HomeScreen)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { text: String ->
                        name = text
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    if(name.isNotBlank()){
                        names.add(name)
                        CoroutineScope(Dispatchers.IO).launch {
                            AppDatabase.getInstance(context).cartDao().insertCart(
                                Cart(
                                    id = 0,
                                    name = name
                                )
                            )
                        }
                    }
                }) {
                    Text(text = "Add")
                }
            }

            LazyColumn{
                items(names){ currentName ->
                    Text(
                        text = currentName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                        )
                    Divider()
                }
            }

        }
    }
}
@Preview
@Composable
fun DefaultPreviewOfShoppingCartScreen(){
    ShoppingCartScreen()
}