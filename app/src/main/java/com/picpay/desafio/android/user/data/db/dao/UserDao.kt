package com.picpay.desafio.android.user.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.user.data.db.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(users: List<UserEntity>)

    @Query("SELECT * FROM picPayUsers")
    fun getUsers(): List<UserEntity>
}