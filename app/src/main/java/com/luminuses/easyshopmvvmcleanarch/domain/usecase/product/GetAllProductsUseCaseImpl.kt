package com.luminuses.easyshopmvvmcleanarch.domain.usecase.product

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetAllProductsUseCase{
    override fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>>
    = repository.getProductsListFromApi()

    override fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> {
        TODO("Not yet implemented")
    }
}