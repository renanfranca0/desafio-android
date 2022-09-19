package com.picpay.desafio.android.repository.remote

import com.picpay.desafio.android.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UsersEndpoint {

    @GET("users")
    suspend fun getUsers(): List<User>
}