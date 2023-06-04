package com.example.recipevault.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE id = :userId")
     fun getUserById(userId: Int): User?

    @Insert
     fun insertUser(user: User)

    @Query("SELECT COUNT(*) FROM User WHERE username = :username OR email = :email")
     fun checkUserExists(username: String, email: String): Int

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
     fun getUserByUsernameAndPassword(username: String, password: String): User?

    @Update
     fun updateUser(user: User)

    @Delete
     fun deleteUser(user: User)
}