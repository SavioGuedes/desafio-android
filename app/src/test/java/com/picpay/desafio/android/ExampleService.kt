package com.picpay.desafio.android

import com.picpay.desafio.android.user.data.network.api.PicPayService
import com.picpay.desafio.android.user.data.network.model.UserResponse

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}