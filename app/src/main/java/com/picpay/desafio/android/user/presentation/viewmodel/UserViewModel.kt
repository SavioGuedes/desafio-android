package com.picpay.desafio.android.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.user.domain.usecase.GetUserUseCase

class UserViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {
}