package com.pethersilva.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.pethersilva.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDao
import com.pethersilva.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.pethersilva.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.pethersilva.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.pethersilva.cleanarchitecturenoteapp.feature_note.domain.use_case.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val noteModule = module {

    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }

    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            getNoteByIdUseCase = GetNoteByIdUseCase(repository),
            insertNoteUseCase = InsertNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }

    single { provideNoteDatabase(androidApplication()) }
    single { provideNoteDao(noteDatabase = get()) }
    single { provideNoteRepository(noteDao = get()) }
    single { provideNoteUseCases(repository = get()) }
}
