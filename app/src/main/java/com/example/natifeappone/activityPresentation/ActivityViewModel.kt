package com.example.natifeappone.activityPresentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.Constants

class ActivityViewModel(private var itemIdFromReceiver: Int): ViewModel() {

    private val _itemId = SingleLiveEvent<Int>()
    val itemId: LiveData<Int> = _itemId

    fun getFragment(savedInstanceState: Bundle?): SingleLiveEvent<Int> {
        if (itemIdFromReceiver != Constants.ID_DEFAULT_VALUE && savedInstanceState == null) {
            _itemId.value = itemIdFromReceiver
        }
        Log.d("ViewModel Activity", _itemId.value.toString())
        return _itemId
    }
}