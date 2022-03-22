package com.odhiambodevelopers.prefillingroomdbdemo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_table")
data class NoteEntity(
    val noteTitle:String,
    val noteDescription:String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
)