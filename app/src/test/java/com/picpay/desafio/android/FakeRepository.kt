package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UsersRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeRepository : UsersRepository {

    private val fakeListUsers = listOf(
        User(img = "img1", name = "Name1", id = 1, username = "Exemplo1"),
        User(img = "img2", name = "Name2", id = 2, username = "Exemplo2"),
        User(img = "img3", name = "Name3", id = 3, username = "Exemplo3"),
    )

    private val fakeUsers = flow {
        emit(fakeListUsers)
    }

    override suspend fun fetchUsers() = fakeUsers

    private val myFlow = MutableSharedFlow<List<User>>()
    suspend fun emit(listUsers: List<User>) = myFlow.emit(listUsers)
}