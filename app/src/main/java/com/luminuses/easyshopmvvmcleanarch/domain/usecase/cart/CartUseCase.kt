package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {

    suspend operator fun invoke(userCartEntity: UserCartEntity) // insertCartToDb

    suspend operator fun invoke(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> // getCartsByUserIdFromLocal

}