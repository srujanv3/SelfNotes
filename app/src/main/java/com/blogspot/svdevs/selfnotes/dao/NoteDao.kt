package com.blogspot.svdevs.selfnotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.blogspot.svdevs.selfnotes.entity.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun allNotes():LiveData<List<Note>>

}