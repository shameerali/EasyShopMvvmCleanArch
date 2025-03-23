package com.luminuses.easyshopmvvmcleanarch.data.source.local

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

interface LocalDataSource {

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun getUserCartByUserIdFromDb(userId: String) : List<UserCartEntity>

    suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity)

    suspend fun updateUserCartFromDb(userCartEntity: UserCartEntity)


}