package com.picpay.desafio.android.user.domain.usecase

import com.picpay.desafio.android.user.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend fun execute() = repository.getUsers()
}