package com.example.natifeappone.presentation.activityPresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActivityViewModelFactory(private var itemIdFromReceiver: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActivityViewModel(itemIdFromReceiver = itemIdFromReceiver) as T
    }
}