package com.example.notesappfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.notesappfirebase.databinding.FragmentCreatenoteBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreatenoteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatenoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = resources.getColor(R.color.mediumturquoise)

        val toolbar:Toolbar = binding.toolbarofcreatenote
        (activity as MainActivity).supportActionBar?.hide()
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseUser= FirebaseAuth.getInstance().currentUser!!

        //lanjutan ada disini :-)

        binding.faSaveNote.setOnClickListener {
            val title:String = binding.etCreateTitleOfNote.text.toString()
            val content:String = binding.etCreateContentOfNote.text.toString()

            if(title.isEmpty() || content.isEmpty()){
                Toast.makeText(activity, "Both field are Require", Toast.LENGTH_SHORT).show()
            } else {
                val documentReference: DocumentReference = firebaseFirestore.collection("notes").document(firebaseUser.uid).collection("MyNotes").document()
                val note = HashMap<String, String>()
                note.put("title", title)
                note.put("content", content)

                documentReference.set(note).addOnSuccessListener{
                    Toast.makeText(activity, "Note Create Succesfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToNotesFragment(), NavOptions.Builder().setPopUpTo(R.id.createNoteFragment, true).build())
                    
                }.addOnFailureListener {
                    Toast.makeText(activity, "Failed To Create Note", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToNotesFragment(), NavOptions.Builder().setPopUpTo(R.id.createNoteFragment, true).build())
                }
            }
        }

    }
}