package com.luminuses.easyshopmvvmcleanarch.data.repository

import com.luminuses.easyshopmvvmcleanarch.data.source.local.LocalDataSource
import com.luminuses.easyshopmvvmcleanarch.di.coroutine.IoDispatcher
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val localDataSource: LocalDataSource,
): LocalRepository {
    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.insertUserCartToDb(userCartEntity)
        }
    }
}