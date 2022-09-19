package com.picpay.desafio.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.model.User

@Database(
    entities = [
        User::class
    ],
    version = 1,
)
abstract class PicPayDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
}