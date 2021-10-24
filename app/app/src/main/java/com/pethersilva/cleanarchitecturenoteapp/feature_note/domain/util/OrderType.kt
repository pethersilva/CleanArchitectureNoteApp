package com.pethersilva.cleanarchitecturenoteapp.feature_note.domain.util

sealed interface OrderType {
    object Ascending: OrderType
    object Descending: OrderType
}