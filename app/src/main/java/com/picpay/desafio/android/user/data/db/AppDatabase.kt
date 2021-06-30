package com.picpay.desafio.android.user.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.user.data.db.dao.UserDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}