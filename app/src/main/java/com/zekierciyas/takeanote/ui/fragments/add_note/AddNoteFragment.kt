package com.zekierciyas.takeanote.ui.fragments.add_note

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zekierciyas.takeanote.models.Note
import com.zekierciyas.takeanote.models.Priority
import com.zekierciyas.takeanote.ui.viewmodels.NotesViewModel
import com.zekierciyas.takeanote.util.TimeUtil
import com.google.android.material.chip.Chip
import com.zekierciyas.takeanote.R
import com.zekierciyas.takeanote.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val notesViewModel: NotesViewModel by viewModels()

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save_note) {
            saveNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val titleText = binding.titleEt.text.toString()
        val contentText = binding.contentEt.text.toString()
        if (notesViewModel.noteIsValid(titleText, contentText)) {
            val note = Note(
                    0, // Database set to auto increment
                    titleText,
                    contentText,
                    TimeUtil.getCurrentTime(),
                    getChipPriority()
            )
            notesViewModel.insertNote(note)
            Toast.makeText(context, "Successfully added note", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNoteFragment_to_notesListFragment)
        } else {
            Toast.makeText(context, "Please fill in the fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getChipPriority(): Priority {
        val chipsCount = binding.chipGroup.childCount
        var i = 0
        while (i < chipsCount) {
            val chip = binding.chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                return Priority.valueOf(chip.text.toString().toUpperCase(Locale.ENGLISH))
            }
            i++
        }
        return Priority.LOW
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}