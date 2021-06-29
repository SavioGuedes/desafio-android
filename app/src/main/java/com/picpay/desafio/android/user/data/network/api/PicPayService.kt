package com.picpay.desafio.android.user.data.network.api

import com.picpay.desafio.android.user.data.network.response.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}