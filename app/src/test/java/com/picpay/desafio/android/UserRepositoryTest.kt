package com.picpay.desafio.android

import com.picpay.desafio.android.repository.UsersRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    private lateinit var usersRepository: UsersRepository

    @Before
    fun setup() {
        usersRepository = FakeRepository()
    }

    @Test
    fun `call users from repository,then return a list of users`() = runTest {

        val value = usersRepository.fetchUsers().first()
        assertTrue(value.isNotEmpty())
        assertTrue(value.size > 1)
    }
}