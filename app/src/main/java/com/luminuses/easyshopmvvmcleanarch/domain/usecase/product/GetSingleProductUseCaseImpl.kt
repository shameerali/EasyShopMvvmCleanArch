package com.luminuses.easyshopmvvmcleanarch.domain.usecase.product

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<NetworkResponseState<DetailProductEntity>> {
       return repository.getSingleProductByIdFromApi(id)
    }
}