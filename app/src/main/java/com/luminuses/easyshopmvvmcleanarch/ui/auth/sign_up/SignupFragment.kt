package com.luminuses.easyshopmvvmcleanarch.ui.auth.sign_up

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentSignupBinding
import com.luminuses.easyshopmvvmcleanarch.ui.auth.UserInformationUiData
import com.luminuses.easyshopmvvmcleanarch.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goToLogin.setOnClickListener(){
            val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.btnCreateAccount.setOnClickListener {
            checkEmptyFields{ user ->
                viewModel.signUp(user)
            }
        }

        observer()
    }

    private fun observer() {
        viewModel.signUp.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    Log.d("TAG", "observer: Error ")
//                    binding.btnCreateAccount.isEnabled = false
                    requireView().showToast(it.message)
//                    checkInternetConnection()
                }
                is ScreenState.Loading -> {
                    Log.d("TAG", "observer: Loading ")
//                    binding.btnCreateAccount.isEnabled = true
//                    checkInternetConnection()
                }
                is ScreenState.Success -> {
                    Log.d("TAG", "observer: Success ")
//                    binding.btnCreateAccount.isEnabled = true
                    requireView().showToast(getString(R.string.sign_up_success))
//                    val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
//                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun checkEmptyFields(
        onSuccess: (UserInformationUiData) -> Unit,
    ){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val name = binding.name.text.toString()
        val surname = binding.surname.text.toString()
        val phone = binding.phone.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {
            onSuccess(
                UserInformationUiData(
                    id = "",
                    name = name,
                    surname = surname,
                    email = email,
                    phone = phone,
                    image = "",
                    password = password,
                    token = "",
                ),
            )
        } else {
            requireView().showToast(getString(R.string.please_not_blanks))
        }
    }

}