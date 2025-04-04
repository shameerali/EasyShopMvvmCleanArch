package com.luminuses.easyshopmvvmcleanarch.domain.entity.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_badges")
data class UserCartBadgeEntity(
    @PrimaryKey val userUniqueInfo: String = "",
    val hasBadge: Boolean = false,
)
