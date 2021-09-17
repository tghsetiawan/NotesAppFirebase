package com.example.notesappfirebase

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappfirebase.databinding.FragmentNotesBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var myRecycleView: RecyclerView
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseFirestore: FirebaseFirestore

    lateinit var noteAdapter: FirestoreRecyclerAdapter<FirebaseModelJava, NoteViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.mediumturquoise)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        firebaseFirestore = FirebaseFirestore.getInstance()

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

        val query: Query = firebaseFirestore.collection("notes").document(firebaseUser.uid).collection("MyNotes").orderBy("title", Query.Direction.ASCENDING)
        val allusernotes: FirestoreRecyclerOptions<FirebaseModelJava> = FirestoreRecyclerOptions.Builder<FirebaseModelJava>().setQuery(query, FirebaseModelJava::class.java).build()

        noteAdapter = object : FirestoreRecyclerAdapter<FirebaseModelJava, NoteViewHolder>(allusernotes){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
                var view:View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_note, parent, false)
                return NoteViewHolder(view)
            }

            override fun onBindViewHolder(p0: NoteViewHolder, p1: Int, p2: FirebaseModelJava) {
                p0.title.setText(p2.title)
                p0.content.setText(p2.content)
            }
        }
        myRecycleView = view.findViewById(R.id.recycleview)
        myRecycleView.setHasFixedSize(true)
        var staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        myRecycleView.apply {
            myRecycleView.layoutManager = staggeredGridLayoutManager
            adapter = noteAdapter
        }
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var content: TextView
        var mnote: LinearLayout? = null

        init {
            title = view.findViewById(R.id.tvNoteTitle)
            content = view.findViewById(R.id.tvNoteContent)
            mnote = view.findViewById(R.id.note)
        }
    }

    private fun backToLoginFragment(){
        Navigation.findNavController(requireView()).popBackStack(R.id.logInFragment, true)
        findNavController().navigateUp()
    }

    override fun onStart() {
        super.onStart()
        noteAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        if(noteAdapter!=null){
            noteAdapter.startListening()
        }
    }
}