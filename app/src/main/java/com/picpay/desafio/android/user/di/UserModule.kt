package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.user.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.user.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserViewModel(get()) }
    factory { GetUserUseCase(get()) }
}