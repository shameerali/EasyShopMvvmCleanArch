package com.luminuses.easyshopmvvmcleanarch.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCart(userCartEntity: UserCartEntity)

    @Query("SELECT * FROM user_carts WHERE userId = :userId")
    suspend fun getCartByUserId(userId: String): List<UserCartEntity>

    @Delete
    suspend fun deleteUserCartItem(userCartEntity: UserCartEntity)

    @Update
    suspend fun updateUserCartItem(userCartEntity: UserCartEntity)

}