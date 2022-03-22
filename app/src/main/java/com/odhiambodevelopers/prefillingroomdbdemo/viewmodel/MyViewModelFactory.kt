package com.odhiambodevelopers.prefillingroomdbdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteDatabase

class MyViewModelFactory constructor(private val noteDatabase: NoteDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(noteDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}