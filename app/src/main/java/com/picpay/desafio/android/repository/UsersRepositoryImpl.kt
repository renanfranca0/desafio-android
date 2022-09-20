package com.picpay.desafio.android.repository

import com.picpay.desafio.android.database.UserDAO
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.PicPayService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val userDao: UserDAO) : UsersRepository {

    override suspend fun fetchUsers(): Flow<List<User>> {
        val users = PicPayService.usersEndpoint.getUsers()
        userDao.insertAll(users)

        return userDao.getAllUsers()
    }
}