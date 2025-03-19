package com.luminuses.easyshopmvvmcleanarch.data.repository

import com.luminuses.easyshopmvvmcleanarch.common.TokenManager
import com.luminuses.easyshopmvvmcleanarch.data.source.remote.FirebaseDataSource
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.FirebaseSignInUserEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    private val tokenManager: TokenManager
) :FirebaseRepository {
    override fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signUpWithFirebase(user, onSuccess, onFailure)
    }

    override fun writeNewUserToFirebaseDatabase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
         firebaseDataSource.writeUserDataToFirebase(user,
             onSuccess ,
             onFailure)
    }

    override fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signInWithFirebase(user,
            onSuccess = {userInformationEntity ->
                tokenManager.saveToken(userInformationEntity.token)
                onSuccess(userInformationEntity)},
            onFailure)
    }
}