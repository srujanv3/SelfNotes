package com.blogspot.svdevs.selfnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.svdevs.selfnotes.db.NoteDatabase
import com.blogspot.svdevs.selfnotes.entity.Note
import com.blogspot.svdevs.selfnotes.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository:NoteRepository
    val allNotes : LiveData<List<Note>>
    init {
        val dao = NoteDatabase.getDatabase(application).getDao() //getting dao instance from NoteDatabase
        repository = NoteRepository(dao) // creating instance of NoteRepository
        allNotes = repository.allNotes //getting all notes via repository
    }

    fun insertNote(note:Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }



}