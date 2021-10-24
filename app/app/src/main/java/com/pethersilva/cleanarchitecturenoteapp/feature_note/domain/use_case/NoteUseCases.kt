package com.pethersilva.cleanarchitecturenoteapp.feature_note.domain.use_case

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)