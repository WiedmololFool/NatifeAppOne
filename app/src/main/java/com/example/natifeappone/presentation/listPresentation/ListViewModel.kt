package com.example.natifeappone.presentation.listPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.natifeappone.data.models.Item
import com.example.natifeappone.data.repository.ItemHolder
import com.example.natifeappone.data.repository.ItemPreferences

class ListViewModel(private val preferences: ItemPreferences) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        getItems()
    }

    private fun getItems() {
        _items.value = ItemHolder.list
    }

    fun saveItemId(id: Int) {
        preferences.setId(id)
    }
}