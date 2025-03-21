package com.luminuses.easyshopmvvmcleanarch.data.source.local

import com.luminuses.easyshopmvvmcleanarch.data.database.AppDao
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDao: AppDao
): LocalDataSource {
    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        appDao.insertUserCart(userCartEntity)
    }
}