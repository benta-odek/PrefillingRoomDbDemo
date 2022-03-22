package com.odhiambodevelopers.prefillingroomdbdemo.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.odhiambodevelopers.prefillingroomdbdemo.R
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteDatabase
import com.odhiambodevelopers.prefillingroomdbdemo.data.local.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import timber.log.Timber
import java.io.BufferedReader

class StartingNotes(private val context: Context) :RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingNotes(context)
        }
    }

    //Filling database with the data from JSON
    private suspend fun fillWithStartingNotes(context: Context){
        //obtaining instance of data access object
        val dao = NoteDatabase.getInstance(context)?.dao

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val notes = loadJSONArray(context)
            if (notes != null){
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until notes.length()){
                    //variable to obtain the JSON object
                    val item = notes.getJSONObject(i)
                    //Using the JSON object to assign data
                    val noteTitle = item.getString("note-title")
                    val notesDescription = item.getString("note-description")

                    //data loaded to the entity
                    val noteEntity = NoteEntity(
                        noteTitle,notesDescription
                    )

                    //using dao to insert data to the database
                    dao?.insertNote(noteEntity)
                }
            }
        }
        //error when exception occurs
        catch (e:JSONException) {
            Timber.d("fillWithStartingNotes: $e")
        }
    }

    // loads JSON data
    private fun loadJSONArray(context: Context):JSONArray?{
        //obtain input byte
        val inputStream = context.resources.openRawResource(R.raw.notes)
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}