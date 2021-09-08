package com.example.notesappfirebase

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentNotesBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.mediumturquoise)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.toolbarofcreatenote.inflateMenu(R.menu.menu)

        binding.faCreatNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment)
        }

        binding.toolbarofcreatenote.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout -> {
                    firebaseAuth.signOut()
                    backToLoginFragment()
//                findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(), NavOptions.Builder().setPopUpTo(R.id.notesFragment, true).build())
                }
            }

            true
        }
    }

    private fun backToLoginFragment(){
        Navigation.findNavController(requireView()).popBackStack(R.id.logInFragment, true)
        findNavController().navigateUp()
    }
}