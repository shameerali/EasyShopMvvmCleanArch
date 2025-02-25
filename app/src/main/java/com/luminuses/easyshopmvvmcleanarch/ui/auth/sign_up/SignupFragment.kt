package com.luminuses.easyshopmvvmcleanarch.ui.auth.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentSignupBinding
import com.luminuses.easyshopmvvmcleanarch.utils.showToast


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

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
            checkEmptyFields()
        }
    }

    private fun checkEmptyFields(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val name = binding.name.text.toString()
        val surname = binding.surname.text.toString()
        val phone = binding.phone.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {

        } else {
            requireView().showToast(getString(R.string.please_not_blanks))
        }
    }

}