package com.example.recipevault.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Insert
    fun insertCart(cart: Cart)

    @Query("SELECT * FROM Cart")
    fun getAllCarts(): List<Cart>

    @Query("DELETE FROM Cart WHERE id = :cartId")
    fun deleteCart(cartId: Int)

}