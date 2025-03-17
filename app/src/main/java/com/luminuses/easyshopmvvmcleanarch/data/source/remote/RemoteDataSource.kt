package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<CategoriesItem>>>

    fun getProductsListFromApi(): Flow<NetworkResponseState<Products>>
}