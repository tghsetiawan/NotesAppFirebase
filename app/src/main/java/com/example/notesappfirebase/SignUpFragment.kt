package com.example.notesappfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(view).popBackStack(R.id.logInFragment, true)
            findNavController().navigateUp()
        }

        binding.btnSignUp.setOnClickListener {
            val mail:String = binding.loginemail.text.toString().trim()
            val password:String = binding.loginpassword.text.toString().trim()

            if(mail.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "All Fields are Required", Toast.LENGTH_LONG).show()
            }
            else if(password.length < 7){
                Toast.makeText(activity, "Password Should Greater than 7 Digits", Toast.LENGTH_LONG).show()
            }
            else{
                // registered the user to firebase
                firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity, "Registration Succesfull", Toast.LENGTH_SHORT).show()
                        sendEmailVerification()
                    }else{
                        Toast.makeText(activity, "Failed To Register", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun sendEmailVerification(){
        firebaseUser= FirebaseAuth.getInstance().currentUser!!
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener {
                if(it.isComplete){
                    Toast.makeText(activity, "Verification Email is Sent, Verify and Log In Again", Toast.LENGTH_SHORT).show()
                    firebaseAuth.signOut()
                    Navigation.findNavController(requireView()).popBackStack(R.id.logInFragment, true)
                    findNavController().navigateUp()
                }
            }
        } else {
            Toast.makeText(activity, "Failed To Send Verification Email", Toast.LENGTH_SHORT).show()
        }

    }

}