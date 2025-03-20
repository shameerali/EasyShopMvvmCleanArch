package com.luminuses.easyshopmvvmcleanarch.data.source.local

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

interface LocalDataSource {

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)


}