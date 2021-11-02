package com.blogspot.svdevs.selfnotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blogspot.svdevs.selfnotes.dao.NoteDao
import com.blogspot.svdevs.selfnotes.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getDao():NoteDao

    //Check for single instance
    companion object{

        private var instance :NoteDatabase? = null

        fun getDatabase(context: Context):NoteDatabase {

            return instance?: synchronized(context){
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database").build()
                instance = inst
                return inst
            }

        }

    }

}