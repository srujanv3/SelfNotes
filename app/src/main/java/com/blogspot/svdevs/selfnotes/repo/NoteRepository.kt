package com.blogspot.svdevs.selfnotes.repo

import androidx.lifecycle.LiveData
import com.blogspot.svdevs.selfnotes.dao.NoteDao
import com.blogspot.svdevs.selfnotes.entity.Note

class NoteRepository(val noteDao:NoteDao) {

    val allNotes:LiveData<List<Note>> = noteDao.allNotes()

    suspend fun insert(note:Note){
        noteDao.insert(note)
    }
    suspend fun delete(note:Note){
        noteDao.delete(note)
    }

  }