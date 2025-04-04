package com.luminuses.easyshopmvvmcleanarch.domain.usecase.product

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>>

    operator fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>>
}