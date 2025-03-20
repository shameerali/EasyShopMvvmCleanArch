package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import android.util.Log
import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.api.ApiService
import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
): RemoteDataSource {
    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<CategoriesItem>>> {
        return flow{
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getAllCategoriesListFromApi()
                Log.d("TAG==>", "getAllCategoriesListFromApi: ")
                Log.d("TAG==>", "getAllCategoriesListFromApi: "+response.toString())
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

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>> {
         return flow {
             emit(NetworkResponseState.Loading)
             try {
                 val response = apiService.getSingleProductByIdFromApi(productId)
                 emit(NetworkResponseState.Success(response))
             }catch (e: Exception){
                 emit(NetworkResponseState.Error(e))
             }

         }
    }
}