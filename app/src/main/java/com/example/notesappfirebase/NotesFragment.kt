package com.example.notesappfirebase

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.notesappfirebase.databinding.FragmentNotesBinding
import com.google.firebase.auth.FirebaseAuth

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title="All Notes"

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.faCreatNote.setOnClickListener {
            findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(), NavOptions.Builder().setPopUpTo(R.id.notesFragment, true).build())
        }
    }

    private fun backToLoginFragment(){
        Navigation.findNavController(requireView()).popBackStack(R.id.logInFragment, true)
        findNavController().navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
//                || super.onOptionsItemSelected(item)

        var itemId: String = item.itemId.toString()
        when(item.itemId){
             R.id.logout -> {
                firebaseAuth.signOut()
                backToLoginFragment()
//                findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(), NavOptions.Builder().setPopUpTo(R.id.notesFragment, true).build())
            }
        }

        return super.onOptionsItemSelected(item)
    }
}