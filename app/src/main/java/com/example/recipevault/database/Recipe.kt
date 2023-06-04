package com.example.recipevault.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class Recipe (
@PrimaryKey(autoGenerate = true) val id: Int = 0,
@ColumnInfo(name = "name")val name: String,
@ColumnInfo(name = "calories")val calories: String,
@ColumnInfo(name = "ingredients")val ingredients: String,
@ColumnInfo(name = "description")val description: String

)

