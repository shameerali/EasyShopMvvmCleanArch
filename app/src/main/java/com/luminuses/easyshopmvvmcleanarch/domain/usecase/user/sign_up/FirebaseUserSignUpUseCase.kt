package com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_up

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity

interface FirebaseUserSignUpUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}