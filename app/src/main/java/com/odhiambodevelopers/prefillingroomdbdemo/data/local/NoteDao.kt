package com.odhiambodevelopers.prefillingroomdbdemo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Query("SELECT * FROM Notes_table ORDER BY id ASC")
     fun getAllNotes(): LiveData<List<NoteEntity>>
}