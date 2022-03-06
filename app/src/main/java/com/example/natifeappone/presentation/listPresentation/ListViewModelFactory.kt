package com.example.natifeappone.presentation.listPresentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.natifeappone.data.repository.ItemPreferences

class ListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(ItemPreferences(context = context)) as T
    }
}