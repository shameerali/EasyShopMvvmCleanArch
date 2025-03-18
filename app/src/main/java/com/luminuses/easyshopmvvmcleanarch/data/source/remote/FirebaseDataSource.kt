package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity

interface FirebaseDataSource {
    fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    fun writeUserDataToFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )
}