package com.picpay.desafio.android.user.data.datasource

import com.picpay.desafio.android.user.data.network.api.ApiResponse
import com.picpay.desafio.android.user.data.network.api.PicPayService
import com.picpay.desafio.android.user.data.network.model.UserResponse

class UserDataSourceImpl(
    private val service: PicPayService
) : UserDataSource {
    override suspend fun getUsers(): ApiResponse<List<UserResponse>> {
        return try {
            val result = service.getUsersService()
            ApiResponse.Success(result)
        }
        catch (error: Exception) {
            ApiResponse.Error
        }
    }
}