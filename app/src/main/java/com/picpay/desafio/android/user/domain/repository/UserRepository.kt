package com.picpay.desafio.android.user.domain.repository

import com.picpay.desafio.android.user.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}