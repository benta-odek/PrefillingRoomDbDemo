package com.odhiambodevelopers.prefillingroomdbdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteDatabase
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteEntity
import kotlinx.coroutines.launch

class MainViewModel(private val noteDatabase: NoteDatabase) : ViewModel() {

    val notes = noteDatabase.dao.getAllNotes()

    fun insertNote(noteEntity: NoteEntity){
        viewModelScope.launch{
            noteDatabase.dao.insertNote(noteEntity)
        }
    }
}