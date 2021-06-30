package com.picpay.desafio.android.user.data.network.api

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T): ApiResponse<T>()
    object Error: ApiResponse<Nothing>()
}
