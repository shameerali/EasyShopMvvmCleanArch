package com.luminuses.easyshopmvvmcleanarch.data.repository

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.source.remote.RemoteDataSource
import com.luminuses.easyshopmvvmcleanarch.di.coroutine.IoDispatcher
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RemoteRepository {
    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
       return remoteDataSource.getAllCategoriesListFromApi().map {
           when(it){
               is NetworkResponseState.Loading -> NetworkResponseState.Loading
               is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
               is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)

           }
       }.flowOn(ioDispatcher)
    }
}