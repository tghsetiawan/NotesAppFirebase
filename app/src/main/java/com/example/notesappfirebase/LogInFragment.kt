package com.example.notesappfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val mail:String = binding.loginemail.text.toString().trim()
            val password:String = binding.loginpassword.text.toString().trim()

            if(mail.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "All Fields are Required", Toast.LENGTH_LONG).show()
            }
            else{
                // login the user
            }

        }

        binding.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
        }
    }
}