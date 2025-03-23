package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

interface DeleteUserCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}