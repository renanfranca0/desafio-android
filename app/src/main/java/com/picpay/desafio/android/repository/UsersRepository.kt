package com.picpay.desafio.android.repository

import com.picpay.desafio.android.database.UserDAO
import com.picpay.desafio.android.network.PicPayService
import javax.inject.Inject

class UsersRepository @Inject constructor(private val userDao: UserDAO) {

    val users = userDao.getAllUsers()

    suspend fun refreshUsers() {
        val users = PicPayService.usersEndpoint.getUsers()
        userDao.insertAll(users)
    }
}