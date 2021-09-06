package com.example.notesappfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnPasswordrecoverbutton.setOnClickListener {
            val email:String = binding.forgotpassword.text.toString().trim()

            if(email.isEmpty()){
                Toast.makeText(activity, "Enter your mail first", Toast.LENGTH_SHORT).show()
            }else{
                //we have to send
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity, "Mail Sent, You can recover your password using mail", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).popBackStack(R.id.logInFragment, true)
                        findNavController().navigateUp()
                    }else{
                        Toast.makeText(activity, "Email is Wrong or Account Not Exist", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.tvgobacktologin.setOnClickListener {
            Navigation.findNavController(view).popBackStack(R.id.logInFragment, true)
            findNavController().navigateUp()
        }
    }
}