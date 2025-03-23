package com.luminuses.easyshopmvvmcleanarch.domain.repository

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun getCartsByUserIdFromDb(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend fun deleteUserCart(userCartEntity: UserCartEntity)

    suspend fun updateUserCart(userCartEntity: UserCartEntity)

}