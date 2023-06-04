package com.example.recipevault.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

@Insert
fun insertRecipe(recipe: Recipe)

@Query("SELECT * FROM Recipe")
fun getAllRecipes(): List<Recipe>

@Query("DELETE FROM Recipe WHERE id = :recipeId")
fun deleteRecipe(recipeId: Int)


}