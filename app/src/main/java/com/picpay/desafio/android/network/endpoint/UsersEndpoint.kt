package com.picpay.desafio.android.network.endpoint

import com.picpay.desafio.android.model.User
import retrofit2.http.GET

interface UsersEndpoint {

    @GET("users")
    suspend fun getUsers(): List<User>
}