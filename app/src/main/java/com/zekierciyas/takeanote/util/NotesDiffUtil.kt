package com.zekierciyas.takeanote.util

import androidx.recyclerview.widget.DiffUtil
import com.zekierciyas.takeanote.models.Note

class NotesDiffUtil(
    private val oldNotesList: List<Note>,
    private val newNotesList: List<Note>
) : DiffUtil.Callback() {

    /**returns the Old Note List size as Int.
     */
    override fun getOldListSize(): Int = oldNotesList.size

    /**returns the New Note List size as Int.
     */
    override fun getNewListSize(): Int = newNotesList.size

    /** Compares old and new Note List sizes and returns Boo if equal.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotesList[oldItemPosition].id == newNotesList[newItemPosition].id
    }

    /** Compares two content of the notes and returns Boo if same.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotesList[oldItemPosition] == newNotesList[newItemPosition]
    }

}