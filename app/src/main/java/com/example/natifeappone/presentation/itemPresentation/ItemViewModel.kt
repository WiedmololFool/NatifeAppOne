package com.example.natifeappone.presentation.itemPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.Constants
import com.example.natifeappone.data.models.Item
import com.example.natifeappone.data.repository.ItemHolder

class ItemViewModel(private var itemId: Int) : ViewModel() {

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    private val _validItemId = MutableLiveData<Boolean>()
    val validItemId: LiveData<Boolean> = _validItemId

    fun getItem() {
        _item.value = itemId.let { ItemHolder.getItem(it) }
    }

    fun validateItemId() {
        if (itemId == Constants.ID_DEFAULT_VALUE) {
            _validItemId.value = false
        } else {
            _validItemId.value = true
            getItem()
        }
    }

}