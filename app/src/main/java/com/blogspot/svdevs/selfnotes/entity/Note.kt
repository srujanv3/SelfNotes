package com.blogspot.svdevs.selfnotes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val note:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}