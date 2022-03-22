package com.odhiambodevelopers.prefillingroomdbdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.odhiambodevelopers.prefillingroomdbdemo.R
import com.odhiambodevelopers.prefillingroomdbdemo.adapter.NotesAdapter
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteDatabase
import com.odhiambodevelopers.prefillingroomdbdemo.databinding.ActivityMainBinding
import com.odhiambodevelopers.prefillingroomdbdemo.viewmodel.MainViewModel
import com.odhiambodevelopers.prefillingroomdbdemo.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    //declaring adapter,viewmodel and binding
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NotesAdapter() }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instance of database
        val noteDatabase = NoteDatabase.getInstance(this)
        val myViewModelFactory = MyViewModelFactory(noteDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.notes.observe(this, Observer { result ->
            adapter.submitList(result)
            binding.recyclerView.adapter = adapter
        })
    }
}