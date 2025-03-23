package com.luminuses.easyshopmvvmcleanarch.domain.usecase.badge

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartBadgeEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCartBadgeUseCaseImpl @Inject constructor(
    private val repository: LocalRepository
): UserCartBadgeUseCase {
    override suspend fun invoke(userCartBadgeEntity: UserCartBadgeEntity) {
        repository.insertUserCartBadgeStateToDb(userCartBadgeEntity)
    }

    override suspend fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>> {
        return repository.getUserCartBadgeStateFromDb(userUniqueInfo)
    }
}