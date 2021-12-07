package com.zekierciyas.takeanote.data.db

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.zekierciyas.takeanote.MyApplication
import com.zekierciyas.takeanote.getOrAwaitValue
import com.zekierciyas.takeanote.models.Note
import com.zekierciyas.takeanote.models.Priority
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NotesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(MyApplication::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: NotesDatabase
    private lateinit var dao: NotesDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getNotesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertNote() = runBlockingTest {
        val date: Long = 234
        val note = Note(1, "First Title", "First Content", date ,Priority.MEDIUM)
        dao.insertNote(note)

        val allShoppingItems = dao.getAllNotes().getOrAwaitValue()

        assertThat(allShoppingItems).contains(note)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteNote() = runBlockingTest {
        val today = Date()
        val date = Date(today.time)
        val note = Note(1, "First Title", "First Content", date.time ,Priority.MEDIUM)
        dao.insertNote(note)
        dao.deleteNote(note)

        val allShoppingItems = dao.getAllNotes().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(note)
    }

}

