package com.luminuses.easyshopmvvmcleanarch.ui.auth.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentSignInBinding
import com.luminuses.easyshopmvvmcleanarch.utils.safeNavigate
import com.luminuses.easyshopmvvmcleanarch.utils.showToast

class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding


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

        binding.btnSignUp.setOnClickListener(){
            val action = SignInFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        navigateToHomeScreen()
    }

    private fun navigateToHomeScreen() {
        findNavController().safeNavigate(SignInFragmentDirections.actionLoginFragmentToHomeFragment())
    }

}