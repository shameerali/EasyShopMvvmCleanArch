package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.api.ApiService
import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
): RemoteDataSource {
    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        return flow{
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getAllCategoriesListFromApi()
                emit(NetworkResponseState.Success(response))
            }catch (e: Exception){
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getProductsListFromApi(): Flow<NetworkResponseState<Products>> {
         return flow {
             emit(NetworkResponseState.Loading)
             try {
                 val response = apiService.getProductsListFromApi()
                 emit(NetworkResponseState.Success(response))

             }catch (e: Exception){
                 emit(NetworkResponseState.Error(e))
             }
         }
    }
}