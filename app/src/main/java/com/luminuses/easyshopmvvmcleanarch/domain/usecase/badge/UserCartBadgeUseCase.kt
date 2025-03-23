package com.luminuses.easyshopmvvmcleanarch.domain.usecase.badge

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartBadgeEntity
import kotlinx.coroutines.flow.Flow

interface UserCartBadgeUseCase {

    suspend operator fun invoke(userCartBadgeEntity: UserCartBadgeEntity)

    suspend operator fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>>
}