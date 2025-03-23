package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import javax.inject.Inject

class UpdateCartUseCaseImpl  @Inject constructor(
    private val localRepository: LocalRepository
) : UpdateCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
         localRepository.updateUserCart(userCartEntity)
    }
}