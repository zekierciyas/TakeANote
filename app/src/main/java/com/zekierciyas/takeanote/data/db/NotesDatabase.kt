package com.zekierciyas.takeanote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zekierciyas.takeanote.models.Note
import com.zekierciyas.takeanote.models.PriorityTypeConverters

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(PriorityTypeConverters::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}