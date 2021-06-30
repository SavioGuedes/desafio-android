package com.picpay.desafio.android.user.data.datasource

import com.picpay.desafio.android.user.data.network.api.ApiResponse
import com.picpay.desafio.android.user.data.network.model.UserResponse

interface UserDataSource {
    suspend fun getUsers(): ApiResponse<List<UserResponse>>
}