package com.example.natifeappone.presentation.itemPresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemViewModelFactory(private val itemId: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemViewModel(itemId = itemId) as T
    }
}