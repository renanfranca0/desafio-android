package com.picpay.desafio.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user")
data class User(
    @SerializedName("img") val img: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") @PrimaryKey val id: Int? = null,
    @SerializedName("username") val username: String? = null
)