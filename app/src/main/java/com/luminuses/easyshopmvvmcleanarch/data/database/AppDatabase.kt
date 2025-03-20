package com.luminuses.easyshopmvvmcleanarch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

@Database(entities = [UserCartEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao() : AppDao
}