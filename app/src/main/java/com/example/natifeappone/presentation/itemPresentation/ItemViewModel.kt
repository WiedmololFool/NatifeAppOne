package com.example.natifeappone.presentation.itemPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.Constants
import com.example.natifeappone.data.models.Item
import com.example.natifeappone.data.repository.ItemHolder

class ItemViewModel(private var itemId: Int) : ViewModel() {

    private val _item = MutableLiveData<Result<Item>>()
    val item: LiveData<Result<Item>> = _item

    private fun getItem() {
        val result = ItemHolder.getItem(itemId)
        _item.value = Result.success(result)
    }

    private fun getNoItem(){
        _item.value = Result.failure(exception = Throwable("noItemException"))
    }

    fun validateItemId() {
        if (itemId == Constants.ID_DEFAULT_VALUE) {
            getNoItem()
        } else {
            getItem()
        }
    }


}