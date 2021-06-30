package com.picpay.desafio.android.user.data.datasource

import com.picpay.desafio.android.user.data.db.UserEntity
import com.picpay.desafio.android.user.data.db.dao.UserDao

class UserDbDataSourceImpl(
    private val userDao: UserDao
) : UserDbDataSource {

    override suspend fun createUsers(userEntity: List<UserEntity>) {
        userDao.saveUsers(userEntity)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }
}