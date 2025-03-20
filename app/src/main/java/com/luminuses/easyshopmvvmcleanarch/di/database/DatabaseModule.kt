package com.luminuses.easyshopmvvmcleanarch.di.database

import android.content.Context
import androidx.room.Room
import com.luminuses.easyshopmvvmcleanarch.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database",
        ).build()
    }

    @Provides
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.appDao()
}