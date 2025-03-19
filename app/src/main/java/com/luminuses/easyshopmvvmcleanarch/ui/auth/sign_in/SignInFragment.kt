package com.luminuses.easyshopmvvmcleanarch.ui.auth.sign_in

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.Constants.PREF_FIREBASE_USERID_KEY
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentSignInBinding
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.FirebaseSignInUserEntity
import com.luminuses.easyshopmvvmcleanarch.utils.checkInternetConnection
import com.luminuses.easyshopmvvmcleanarch.utils.safeNavigate
import com.luminuses.easyshopmvvmcleanarch.utils.showToast
import com.luminuses.easyshopmvvmcleanarch.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding

    private val viewModel: SigInViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoginButton()
        setupObservers()

        binding.btnSignUp.setOnClickListener(){
            val action = SignInFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupObservers() {
        viewModel.firebaseLoginState.observe(viewLifecycleOwner) {
                firebaseLoginState ->
            when (firebaseLoginState) {
                ScreenState.Loading -> binding.apply {
                    Log.d("TAG==>", "setupObservers: Loading ")
//                    loading.visible()
                    btnSignIn.isEnabled = false
                }
                is ScreenState.Error -> {
                    Log.d("TAG==>", "setupObservers: Error ")
                    binding.apply {
//                        loading.gone()
                        btnSignIn.isEnabled = true
                    }
                    checkInternetConnection()
                    requireView().showToast(firebaseLoginState.message)
                }
                is ScreenState.Success -> {
                    Log.d("TAG==>", "setupObservers: Success ")
                    binding.apply {
//                        loading.gone()
                        btnSignIn.isEnabled = true
                    }
                    navigateToHomeScreen()
                    requireView().showToast("Welcome ${firebaseLoginState.uiData.name}")
                    saveUserIdToSharedPref(firebaseLoginState.uiData.id)
                }
            }
        }
    }

    private fun saveUserIdToSharedPref(id: String) {
        sharedPref.edit()
            .putString(PREF_FIREBASE_USERID_KEY, id)
            .apply()
    }

    private fun setupLoginButton() {
        binding.btnSignIn.setOnClickListener(){
            firebaseLoginLogic()
        }
    }

    private fun firebaseLoginLogic() {
        val email = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isBlank() || password.isBlank()) {
            requireView().showToast(getString(R.string.please_not_blanks))
            return
        }
        val user = FirebaseSignInUserEntity(email, password)
        viewModel.loginWithFirebase(user)

    }

    private fun navigateToHomeScreen() {
        findNavController().safeNavigate(SignInFragmentDirections.actionLoginFragmentToHomeFragment())
    }

}