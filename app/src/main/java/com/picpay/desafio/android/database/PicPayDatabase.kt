package com.picpay.desafio.android.dao

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

    companion object {
        @Volatile
        private var INSTANCE: PicPayDatabase? = null

        fun getDatabase(context: Context): PicPayDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicPayDatabase::class.java,
                    "picpay_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}