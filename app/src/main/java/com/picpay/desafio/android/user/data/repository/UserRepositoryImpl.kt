package com.picpay.desafio.android.user.data.repository

import com.picpay.desafio.android.user.data.datasource.UserDataSource
import com.picpay.desafio.android.user.data.datasource.UserDbDataSource
import com.picpay.desafio.android.user.data.db.responseToUserList
import com.picpay.desafio.android.user.data.db.toUserEntityList
import com.picpay.desafio.android.user.data.db.toUserList
import com.picpay.desafio.android.user.data.network.api.ApiResponse
import com.picpay.desafio.android.user.data.network.model.UserResponse
import com.picpay.desafio.android.user.domain.model.User
import com.picpay.desafio.android.user.domain.repository.UserRepository
import retrofit2.HttpException

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val userDbDataSource: UserDbDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        val usersList : ArrayList<User> = ArrayList()
        try {
            when (val result = userDataSource.getUsers()) {
                is ApiResponse.Success -> {
                    result.data.let { data ->
                        registerUserListDb(data)
                        usersList.addAll(data.responseToUserList())
                    }
                }
                is ApiResponse.Error -> {
                    usersList.addAll(getUserListDb())
                }
            }
        }
        catch (error: HttpException) {
            usersList.addAll(getUserListDb())
        }
        return usersList
    }

    private suspend fun registerUserListDb(data: List<UserResponse>) {
        userDbDataSource.createUsers(data.toUserEntityList())
    }

    private suspend fun getUserListDb(): List<User> {
        return userDbDataSource.getUsers().toUserList()
    }
}