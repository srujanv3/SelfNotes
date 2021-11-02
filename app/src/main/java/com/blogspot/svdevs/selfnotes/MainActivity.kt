package com.blogspot.svdevs.selfnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.svdevs.selfnotes.adapter.NotesAdapter
import com.blogspot.svdevs.selfnotes.entity.Note
import com.blogspot.svdevs.selfnotes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), NotesAdapter.adpaterInterface {

    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rView:RecyclerView = findViewById(R.id.recycler_view)
        val adapter = NotesAdapter(this,this)
        rView.layoutManager = LinearLayoutManager(this)
        rView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                    adapter.updateList(it)
            }
        })
    }

    override fun onItemClick(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(applicationContext,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    fun addNote(view: View) {
        val noteText : EditText = findViewById(R.id.et_note)
        var input = noteText.text.toString()
        if(input.isNotEmpty()) {
            viewModel.insertNote(Note(input))
            Toast.makeText(applicationContext,"Note Saved",Toast.LENGTH_SHORT).show()
        }
        noteText.setText("")
    }
}