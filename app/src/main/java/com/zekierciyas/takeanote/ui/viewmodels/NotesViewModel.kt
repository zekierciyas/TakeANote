package com.zekierciyas.takeanote.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zekierciyas.takeanote.models.Note
import com.zekierciyas.takeanote.repositories.DataStoreRepository
import com.zekierciyas.takeanote.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel
@Inject constructor(
    private val repository: NotesRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _allNotes: LiveData<List<Note>> = repository.getAllNotes
    val allNotes: LiveData<List<Note>>
        get() = _allNotes

    private val _readFromDataStore: LiveData<String> = dataStoreRepository.readFromDataStore.asLiveData()
    val readFromDataStore: LiveData<String>
        get() = _readFromDataStore

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

    fun saveToDataStore(sortBy: String) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveToDataStore(sortBy)
    }

    fun noteIsValid(titleText: String, contentText: String): Boolean {
        return titleText.isNotEmpty() && contentText.isNotEmpty()
    }

}