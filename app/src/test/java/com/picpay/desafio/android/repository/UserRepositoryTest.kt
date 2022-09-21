package com.picpay.desafio.android.repository

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

    @Test
    fun `call user by id, then return only one user`() = runTest {
        val value = usersRepository.getUser(1)

        assertTrue(value.first().id == 1)
    }
}