package com.picpay.desafio.android.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>
}