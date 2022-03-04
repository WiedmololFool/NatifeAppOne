package com.example.natifeappone.itemPresentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.repository.ItemHolder

class ItemViewModel(private var itemId: Int?) : ViewModel() {

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    init {
        getItem()
    }

    private fun getItem(){
        _item.value = itemId?.let { ItemHolder.getItem(it) }
        Log.d("ItemViewModel getItem", _item.value.toString())
    }

}