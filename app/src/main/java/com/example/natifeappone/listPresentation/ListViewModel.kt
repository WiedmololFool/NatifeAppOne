package com.example.natifeappone.listPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.itemPresentation.Item
import com.example.natifeappone.repository.ItemHolder
import com.example.natifeappone.repository.ItemPreferences

class ListViewModel(private val preferences: ItemPreferences) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        getItems()
    }

    fun getItems() {
        _items.value = ItemHolder.list
    }

    fun saveItemId(id: Int) {
        preferences.setId(id)
    }
}