package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun fetchUsers(): Flow<List<User>>

    suspend fun getUser(userId: Int): Flow<User>
}