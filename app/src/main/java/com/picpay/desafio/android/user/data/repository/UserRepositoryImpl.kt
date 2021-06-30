package com.picpay.desafio.android.user.data.repository

import com.picpay.desafio.android.user.data.datasource.UserDataSource
import com.picpay.desafio.android.user.data.network.api.ApiResponse
import com.picpay.desafio.android.user.domain.model.User
import com.picpay.desafio.android.user.domain.repository.UserRepository
import retrofit2.HttpException

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        val usersList : ArrayList<User> = ArrayList()
        try {
            when (val result = userDataSource.getUsers()) {
                is ApiResponse.Success -> {
                    result.data.let { data ->
                        data.map { user ->
                            usersList.add(
                                User(
                                    user.img,
                                    user.name,
                                    user.id,
                                    user.username
                                )
                            )
                        }
                    }
                }
                is ApiResponse.Error -> {
                    //TODO: get local value
                }
            }
        }
        catch (error: HttpException) {
            //TODO: get local value
        }
        return usersList
    }
}