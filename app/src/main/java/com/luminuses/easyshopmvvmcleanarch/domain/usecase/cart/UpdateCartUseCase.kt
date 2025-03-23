package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

interface UpdateCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}