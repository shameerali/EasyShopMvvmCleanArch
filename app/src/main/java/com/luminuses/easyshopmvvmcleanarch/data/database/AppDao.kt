package com.luminuses.easyshopmvvmcleanarch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCart(userCartEntity: UserCartEntity)
}