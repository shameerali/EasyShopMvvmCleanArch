package com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.write_user

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity

interface WriteFirebaseUserInfosUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}