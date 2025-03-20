package com.luminuses.easyshopmvvmcleanarch.domain.usecase.product

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import kotlinx.coroutines.flow.Flow

interface GetSingleProductUseCase {
    operator fun invoke(id: Int): Flow<NetworkResponseState<DetailProductEntity>>
}