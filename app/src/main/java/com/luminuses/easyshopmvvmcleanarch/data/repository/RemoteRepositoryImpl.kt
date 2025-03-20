package com.luminuses.easyshopmvvmcleanarch.data.repository

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import com.luminuses.easyshopmvvmcleanarch.data.source.remote.RemoteDataSource
import com.luminuses.easyshopmvvmcleanarch.di.coroutine.IoDispatcher
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val allProductsMapper: ProductListMapper<Product, ProductEntity>,
    private val allCategoriesMapper: ProductListMapper<CategoriesItem, String>,
    private val singleProductMapper : ProductBaseMapper< Product, DetailProductEntity>
) : RemoteRepository {
    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        return remoteDataSource.getAllCategoriesListFromApi().map {
            when(it){
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
                is NetworkResponseState.Success -> {
                    NetworkResponseState.Success(allCategoriesMapper.map(it.result))
                }
            }
        }.flowOn(ioDispatcher)
    }
//    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<CategoriesItem>>> {
//       return remoteDataSource.getAllCategoriesListFromApi().map {
//           when(it){
//               is NetworkResponseState.Loading -> NetworkResponseState.Loading
//               is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
//               is NetworkResponseState.Success -> {
////                   NetworkResponseState.Success(it.result)
//                   NetworkResponseState.Loading
//               }
//
//           }
//       }.flowOn(ioDispatcher)
//    }

    override fun getProductsListFromApi(): Flow<NetworkResponseState<List<ProductEntity>>> {
         return remoteDataSource.getProductsListFromApi().map {
             when(it){
                 is NetworkResponseState.Loading -> NetworkResponseState.Loading
                 is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
                 is NetworkResponseState.Success -> NetworkResponseState.Success(allProductsMapper.map(it.result.products))

             }
         }.flowOn(ioDispatcher)
    }

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<DetailProductEntity>> {
        return remoteDataSource.getSingleProductByIdFromApi(productId).map {
                when(it){
                    is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
                    is NetworkResponseState.Loading -> NetworkResponseState.Loading
                    is NetworkResponseState.Success -> NetworkResponseState.Success(singleProductMapper.map(it.result))
                }
            }.flowOn(ioDispatcher)

    }
}