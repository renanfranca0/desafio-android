package com.picpay.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.database.PicPayDatabase
import com.picpay.desafio.android.database.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context) =
         Room.databaseBuilder(
                context.applicationContext,
                PicPayDatabase::class.java,
                "picpay_database"
        ).build()

    @Singleton
    @Provides
    fun provideUserDao(database: PicPayDatabase): UserDAO {
        return database.userDAO()
    }
}