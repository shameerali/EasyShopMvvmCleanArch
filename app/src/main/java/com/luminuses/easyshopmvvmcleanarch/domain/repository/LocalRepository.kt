package com.luminuses.easyshopmvvmcleanarch.domain.repository

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity

interface LocalRepository {

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

}