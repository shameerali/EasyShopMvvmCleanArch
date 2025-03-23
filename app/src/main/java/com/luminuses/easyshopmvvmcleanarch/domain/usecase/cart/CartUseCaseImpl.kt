package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : CartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
         localRepository.insertUserCartToDb(userCartEntity)
    }

    override suspend fun invoke(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> {
         return  localRepository.getCartsByUserIdFromDb(userId)
    }
}