package com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_in

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.FirebaseSignInUserEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseUserSingInUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): FirebaseUserSingInUseCase {
    override fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseRepository.signInWithFirebase(user, onSuccess, onFailure)
    }
}