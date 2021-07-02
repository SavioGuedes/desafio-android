package com.picpay.desafio.android.user.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.R
import com.picpay.desafio.android.user.domain.model.User
import com.picpay.desafio.android.user.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.user.util.InstantCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = InstantCoroutineRule()

    @Mock
    private lateinit var usersLiveDataObserver: Observer<List<User>>

    @Mock
    private lateinit var errorLiveDataObserver: Observer<Int>

    @Mock
    private lateinit var loadingLiveDataObserver: Observer<Boolean>

    @Mock
    private lateinit var getUsersUseCase: GetUserUseCase

    private lateinit var viewModel: UserViewModel

    @Test
    fun `when getUsers returns not empty list then sets users LiveData`() {
        val usersMock = listOf(
            User("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", 1, "Tod86")
        )
        whenever( runBlocking { getUsersUseCase.execute() } ) doReturn usersMock

        viewModel = UserViewModel(getUsersUseCase).apply {
            users.observeForever(usersLiveDataObserver)
            loading.observeForever(loadingLiveDataObserver)
        }
        viewModel.getUsers()

        verify(usersLiveDataObserver).onChanged(usersMock)
        verify(loadingLiveDataObserver).onChanged(false)
    }

    @Test
    fun `when getUsers returns empty list then sets error LiveData`() {
        whenever( runBlocking { getUsersUseCase.execute() } ) doReturn listOf()

        viewModel = UserViewModel(getUsersUseCase).apply {
            error.observeForever(errorLiveDataObserver)
            loading.observeForever(loadingLiveDataObserver)
        }
        viewModel.getUsers()

        verify(errorLiveDataObserver).onChanged(R.string.error)
        verify(loadingLiveDataObserver).onChanged(false)
    }
}