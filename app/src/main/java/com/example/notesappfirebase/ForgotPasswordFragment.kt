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

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

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

        binding.btnPasswordrecoverbutton.setOnClickListener {
            val email:String = binding.forgotpassword.text.toString().trim()

            if(email.isEmpty()){
                Toast.makeText(activity, "Enter your mail first", Toast.LENGTH_SHORT).show()
            }else{
                //we have to send
            }
        }

        binding.tvgobacktologin.setOnClickListener {
            Navigation.findNavController(view).popBackStack(R.id.logInFragment, true)
            findNavController().navigateUp()
        }
    }
}