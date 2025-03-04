package com.luminuses.easyshopmvvmcleanarch.domain.repository

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

//    fun getProductsListFromApi(): Flow<NetworkResponseState<List<ProductEntity>>>

    fun getAllCategoriesListFromApi() : Flow<NetworkResponseState<List<String>>>
}