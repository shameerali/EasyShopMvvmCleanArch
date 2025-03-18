package com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.write_user

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.repository.FirebaseRepository
import javax.inject.Inject

class WriteFirebaseUserInfosUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
):WriteFirebaseUserInfosUseCase {
    override fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseRepository.writeNewUserToFirebaseDatabase(user, onSuccess, onFailure)
    }
}