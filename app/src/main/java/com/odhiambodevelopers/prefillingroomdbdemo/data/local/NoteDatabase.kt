package com.odhiambodevelopers.prefillingroomdbdemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.odhiambodevelopers.prefillingroomdbdemo.data.StartingNotes

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val dao:NoteDao

    companion object{
        @Volatile
        private var instance:NoteDatabase? = null

        fun getInstance(context: Context):NoteDatabase?{
            if (instance == null){
                synchronized(NoteDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "notes"
                    )
                        .addCallback(StartingNotes(context))
                        .build()
                }
            }
            return instance
        }
    }
}