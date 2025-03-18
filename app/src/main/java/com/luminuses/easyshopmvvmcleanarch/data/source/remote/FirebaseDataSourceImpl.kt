package com.luminuses.easyshopmvvmcleanarch.data.source.remote

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) :FirebaseDataSource {

    private val collection = firestore.collection("users")

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

    override fun writeUserDataToFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val userMap = hashMapOf(
            "id" to firebaseAuth.uid,
            "name" to user.name,
            "surname" to user.surname,
            "email" to user.email,
            "phone" to user.phone,
            "image" to user.image,
        )
        val firebaseUser = firebaseAuth.currentUser
        val profileUpdate = userProfileChangeRequest {
            displayName = userMap["name"]
            photoUri = Uri.parse(userMap["image"])
        }
        firebaseUser?.updateProfile(profileUpdate)

        collection.document(firebaseAuth.uid!!).set(userMap)
            .addOnSuccessListener{
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }

    }
}