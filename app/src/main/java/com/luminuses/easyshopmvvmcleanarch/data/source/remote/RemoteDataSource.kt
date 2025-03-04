package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>>
}