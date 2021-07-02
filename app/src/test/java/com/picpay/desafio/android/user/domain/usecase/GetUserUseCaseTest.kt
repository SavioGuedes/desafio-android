package com.picpay.desafio.android.user.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.user.domain.model.User
import com.picpay.desafio.android.user.domain.repository.UserRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var getUsersUseCase: GetUserUseCase

    @Before
    fun setUp() {
        getUsersUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `when getUsers returns not empty list`() = runBlocking {
        val usersMock = listOf(
            User("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", 1, "Tod86")
        )
        whenever(repository.getUsers()) doReturn usersMock

        val users = getUsersUseCase.execute()

        assertFalse(users.isEmpty())
    }

    @Test
    fun `when getUsers returns empty list`() = runBlocking {
        whenever(repository.getUsers()) doReturn listOf()

        val users = getUsersUseCase.execute()

        assertTrue(users.isEmpty())
    }
}