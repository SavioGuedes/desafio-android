package com.picpay.desafio.android.user.di

import androidx.room.Room
import com.picpay.desafio.android.user.data.datasource.UserDataSource
import com.picpay.desafio.android.user.data.datasource.UserDataSourceImpl
import com.picpay.desafio.android.user.data.datasource.UserDbDataSource
import com.picpay.desafio.android.user.data.datasource.UserDbDataSourceImpl
import com.picpay.desafio.android.user.data.db.AppDatabase
import com.picpay.desafio.android.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.user.domain.repository.UserRepository
import com.picpay.desafio.android.user.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.user.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserViewModel(get()) }
    single { GetUserUseCase(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<UserDbDataSource> { UserDbDataSourceImpl(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database").build()
    }
    single { get<AppDatabase>().userDao() }
}