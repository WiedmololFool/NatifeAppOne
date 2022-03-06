package com.example.natifeappone.presentation.activityPresentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.Constants
import com.example.natifeappone.utils.SingleLiveEvent

class ActivityViewModel(private var itemIdFromReceiver: Int): ViewModel() {

    private val _itemId = SingleLiveEvent<Int>()
    val itemId: LiveData<Int> = _itemId

    fun getItemFragment() {
        if (itemIdFromReceiver != Constants.ID_DEFAULT_VALUE) {
            _itemId.value = itemIdFromReceiver
        }
        Log.d("ViewModel Activity", _itemId.value.toString())
    }
}