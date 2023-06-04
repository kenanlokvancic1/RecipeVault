package com.example.recipevault.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Cart(

@PrimaryKey(autoGenerate = true) val id: Int = 0,
@ColumnInfo(name = "name")val name: String,

)
