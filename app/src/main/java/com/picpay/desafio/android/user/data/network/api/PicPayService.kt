package com.picpay.desafio.android.user.data.network.api

import com.picpay.desafio.android.user.data.network.model.UserResponse
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsersService(): List<UserResponse>
}