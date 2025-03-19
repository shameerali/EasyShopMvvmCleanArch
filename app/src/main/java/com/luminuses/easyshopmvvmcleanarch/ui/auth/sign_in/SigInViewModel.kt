package com.luminuses.easyshopmvvmcleanarch.ui.auth.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.FirebaseSignInUserEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_in.FirebaseUserSingInUseCase
import com.luminuses.easyshopmvvmcleanarch.ui.auth.UserInformationUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val firebaseUserSingInUseCase: FirebaseUserSingInUseCase,
    private val firebaseUserInfoToUiData: ProductBaseMapper<UserInformationEntity, UserInformationUiData>,
) : ViewModel() {
    private val _firebaseLoginState = MutableLiveData<ScreenState<UserInformationUiData>>()
    val firebaseLoginState: LiveData<ScreenState<UserInformationUiData>> get() = _firebaseLoginState

    fun loginWithFirebase(user: FirebaseSignInUserEntity) {
        viewModelScope.launch {
            _firebaseLoginState.value = ScreenState.Loading
            firebaseUserSingInUseCase.invoke(
                user,
                onSuccess = {
                    _firebaseLoginState.value =
                        ScreenState.Success(
                            firebaseUserInfoToUiData.map(
                                it
                            )
                        )
                }
            ){
                _firebaseLoginState.value = ScreenState.Error(it)
            }

        }
    }
}