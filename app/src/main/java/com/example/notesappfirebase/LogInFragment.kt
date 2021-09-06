package com.example.notesappfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LogInFragment : Fragment(){
    private lateinit var binding: FragmentLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

//        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            if (user != null) {
//                // User is signed in
//                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToNotesFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
//            } else {
//                // User is signed out
////                Toast.makeText(activity, "No User LogIn", Toast.LENGTH_LONG).show()
////                findNavController().navigate(R.id.action_login)
//            }
//        }

        //Masih error
//        firebaseUser= FirebaseAuth.getInstance().currentUser!!
//        if(firebaseUser!=null){
//            //masuk ke halaman notes
//            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToNotesFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
////            Toast.makeText(activity, "Isi", Toast.LENGTH_LONG).show()
////            firebaseAuth.signOut()
////            findNavController().navigate(R.id.action_login)
//        }

        binding.btnLogin.setOnClickListener {
            val mail:String = binding.loginemail.text.toString().trim()
            val password:String = binding.loginpassword.text.toString().trim()

            if(mail.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "All Fields are Required", Toast.LENGTH_LONG).show()
            }
            else{
                // login the user
                firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        checkMailVerification()
                    }else{
                        Toast.makeText(activity, "Account Doesn't Exist", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

        binding.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
        }
    }

    private fun checkMailVerification(){
        firebaseUser= FirebaseAuth.getInstance().currentUser!!
        if(firebaseUser.isEmailVerified == true){
            Toast.makeText(activity, "Logged In", Toast.LENGTH_LONG).show()
            //masuk ke halaman notes
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToNotesFragment(), NavOptions.Builder().setPopUpTo(R.id.logInFragment, true).build())
        }else{
            Toast.makeText(activity, "Verify your mail first", Toast.LENGTH_LONG).show()
            firebaseAuth.signOut()
        }
    }
}