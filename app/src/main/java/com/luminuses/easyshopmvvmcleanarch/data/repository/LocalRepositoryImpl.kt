package com.luminuses.easyshopmvvmcleanarch.data.repository

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.data.source.local.LocalDataSource
import com.luminuses.easyshopmvvmcleanarch.di.coroutine.IoDispatcher
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartBadgeEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override suspend fun getCartsByUserIdFromDb(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> {
        return flow {
            val data = localDataSource.getUserCartByUserIdFromDb(userId)
            emit(NetworkResponseState.Success(data))
        }.flowOn(ioDispatcher)
    }

    override suspend fun deleteUserCart(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.deleteUserCartFromDb(userCartEntity)
        }
    }

    override suspend fun updateUserCart(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.updateUserCartFromDb(userCartEntity)
        }
    }

    override suspend fun insertUserCartBadgeStateToDb(userBadge: UserCartBadgeEntity) {
        withContext(ioDispatcher) {
            localDataSource.insertUserCartBadgeCountToDb(userBadge)
        }
    }

    override suspend fun getUserCartBadgeStateFromDb(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>> {
       return flow {
           try {
               emit(NetworkResponseState.Success(localDataSource.getUserCartBadgeStateFromDb(userUniqueInfo)))
           }catch (e: Exception) {
               emit(NetworkResponseState.Success(UserCartBadgeEntity("", false)))
           }

       }.flowOn(ioDispatcher)
    }
}