package com.example.modelnotes.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modelnotes.R
import com.example.modelnotes.databinding.ActivityNoteAddBinding
import com.example.modelnotes.model.Note
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class NoteAdd : AppCompatActivity() {

    private lateinit var binding: ActivityNoteAddBinding
    private lateinit var note: Note
    private lateinit var oldNote: Note

    var isUpdateBoolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(oldNote.title)
            //Bellow to retrieve data from my TextView on MainActivity see more on NoteAdapter
            binding.etDesc.setText(oldNote.note)

            isUpdateBoolean = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.tvCheckFinish.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val noteDesc = binding.etDesc.text.toString()

            if (title.isNotEmpty() || noteDesc.isNotEmpty()) {
                //val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                val formatter = SimpleDateFormat("EEE, d MMM yyyy")

                if (isUpdateBoolean) {
                    note = Note(
                        oldNote.id, title, noteDesc, formatter.format(Date())
                    )
                } else {
                    note = Note(
                        null, title, noteDesc, formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@NoteAdd, "We need data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrowIcon.setOnClickListener {
            onBackPressed()
        }

    }
}