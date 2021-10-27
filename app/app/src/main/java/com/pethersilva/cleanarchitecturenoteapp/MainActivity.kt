package com.pethersilva.cleanarchitecturenoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pethersilva.cleanarchitecturenoteapp.databinding.ActivityMainBinding
import com.pethersilva.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.pethersilva.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val noteDatabase: NoteDatabase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        CoroutineScope(IO).launch {
            noteDatabase.noteDao.insertNote(Note("Título", "Conteúdo", 1, 1, 10))

            val myNote = noteDatabase.noteDao.getNoteById(10)
            print(myNote?.title)
        }
    }
}