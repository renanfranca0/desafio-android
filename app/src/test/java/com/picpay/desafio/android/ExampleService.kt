package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.PicPayService

class FakeService(
    private val service: PicPayService
) {

    suspend fun getUsers(): List<User> {
        return service.usersEndpoint.getUsers()
    }

/*    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }*/
}