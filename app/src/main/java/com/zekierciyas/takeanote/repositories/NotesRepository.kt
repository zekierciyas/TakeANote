package com.zekierciyas.takeanote.repositories

import androidx.lifecycle.LiveData
import com.zekierciyas.takeanote.data.db.NotesDao
import com.zekierciyas.takeanote.models.Note
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NotesRepository @Inject constructor(
    private val notesDao: NotesDao
) {

    val getAllNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insertNote(note: Note) = notesDao.insertNote(note)

    suspend fun updateNote(note: Note) = notesDao.updateNote(note)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()

}