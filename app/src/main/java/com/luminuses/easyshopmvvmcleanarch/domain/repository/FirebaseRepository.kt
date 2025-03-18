package com.luminuses.easyshopmvvmcleanarch.domain.repository

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity

interface FirebaseRepository {
    fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun writeNewUserToFirebaseDatabase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}