package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.user.data.datasource.UserDataSource
import com.picpay.desafio.android.user.data.datasource.UserDataSourceImpl
import com.picpay.desafio.android.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.user.domain.repository.UserRepository
import com.picpay.desafio.android.user.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.user.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserViewModel(get()) }
    single { GetUserUseCase(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
}