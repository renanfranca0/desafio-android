package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.PicPayService

class UsersRepository {

    suspend fun getUsers(): List<User> {
        return PicPayService.usersEndpoint.getUsers()
    }
}