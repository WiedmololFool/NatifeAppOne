package com.example.natifeappone.presentation.itemPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.data.models.Item
import com.example.natifeappone.data.repository.ItemHolder

class ItemViewModel(private var itemId: Int) : ViewModel() {

    private val _item = MutableLiveData<Result<Item>>()
    val item: LiveData<Result<Item>> = _item

    fun getItem() {
        _item.value = runCatching {
            ItemHolder.getItem(itemId)
        }
    }
}