package com.example.recipevault.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipevault.database.AppDatabase
import com.example.recipevault.database.Recipe
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import com.example.recipevault.navigation.SystemBackButtonHandler
import com.example.recipevault.ui.theme.BlueViolet1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen() {
    val context = LocalContext.current
    val recipes = remember { mutableStateListOf<Recipe>() }
    val showAddRecipeDialog = remember { mutableStateOf(false) }

    LaunchedEffect(CoroutineScope(Dispatchers.IO)) {

    }

    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(BlueViolet1)) {
            SystemBackButtonHandler {
                RecipeVaultAppRouter.navigateTo(Screen.HomeScreen)
            }
            TopAppBar(
                title = { Text(text = "Recipes") },
                navigationIcon = {
                    IconButton(onClick = { RecipeVaultAppRouter.navigateTo(Screen.HomeScreen) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showAddRecipeDialog.value = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )

            Box(Modifier.weight(1f)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(recipes) { recipe ->
                        Column(Modifier.padding(16.dp)) {
                            Text(text = recipe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Calories: ${recipe.calories}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Ingredients: ${recipe.ingredients}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Description: ${recipe.description}")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider()
                    }
                }
            }
        }
    }

    if (showAddRecipeDialog.value) {
        AddRecipeScreen(
            onRecipeAdded = { recipe ->
                recipes.add(recipe)
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getInstance(context).recipeDao().insertRecipe(recipe)
                }
                showAddRecipeDialog.value = false
            },
            onDismiss = { showAddRecipeDialog.value = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    onRecipeAdded: (Recipe) -> Unit,
    onDismiss: () -> Unit
) {
    // State for capturing user input
    var name by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            Text("Add Recipe", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("Calories") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ingredients,
                onValueChange = { ingredients = it },
                label = { Text("Ingredients") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val recipe = Recipe(0,name, calories, ingredients, description)
                    onRecipeAdded(recipe)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Add to Recipes")
            }
        }
    }
}



@Preview
@Composable
fun DefaultPreviewOfRecipeScreen(){
    RecipeScreen()
}