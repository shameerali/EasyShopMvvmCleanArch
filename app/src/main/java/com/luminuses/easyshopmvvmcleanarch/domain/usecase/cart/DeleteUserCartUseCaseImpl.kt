package com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteUserCartUseCaseImpl  @Inject constructor(
    private val localRepository: LocalRepository
)  : DeleteUserCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
         localRepository.deleteUserCart(userCartEntity)
    }
}