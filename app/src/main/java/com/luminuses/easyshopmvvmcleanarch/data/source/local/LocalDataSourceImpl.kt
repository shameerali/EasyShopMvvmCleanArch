package com.luminuses.easyshopmvvmcleanarch.data.source.local

import com.luminuses.easyshopmvvmcleanarch.data.database.AppDao
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartBadgeEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDao: AppDao
): LocalDataSource {
    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        appDao.insertUserCart(userCartEntity)
    }

    override suspend fun getUserCartByUserIdFromDb(userId: String): List<UserCartEntity> {
        return appDao.getCartByUserId(userId)
    }

    override suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity) {
         appDao.deleteUserCartItem(userCartEntity)
    }

    override suspend fun updateUserCartFromDb(userCartEntity: UserCartEntity) {
        appDao.updateUserCartItem(userCartEntity)
    }

    override suspend fun insertUserCartBadgeCountToDb(userBadge: UserCartBadgeEntity) {
        appDao.insertUserBadge(userBadge)
    }

    override suspend fun getUserCartBadgeStateFromDb(userUniqueInfo: String): UserCartBadgeEntity {
        return appDao.getUserBadge(userUniqueInfo)
    }
}