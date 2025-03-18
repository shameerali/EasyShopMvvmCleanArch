package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) :FirebaseDataSource {
    override fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener{
                onFailure(it.message ?: "An error occurred")
            }
    }
}