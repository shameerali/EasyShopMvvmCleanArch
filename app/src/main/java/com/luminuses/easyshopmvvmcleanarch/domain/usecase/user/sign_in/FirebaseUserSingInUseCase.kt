package com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_in

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.FirebaseSignInUserEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity

interface FirebaseUserSingInUseCase {
    operator fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}