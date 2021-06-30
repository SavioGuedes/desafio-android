package com.picpay.desafio.android.user.data.datasource

import com.picpay.desafio.android.user.data.db.UserEntity

interface UserDbDataSource {

    suspend fun createUsers(userEntity: List<UserEntity>)
    suspend fun getUsers(): List<UserEntity>
}