package com.example.natifeappone.listPresentation
import com.example.natifeappone.itemPresentation.Item
import com.example.natifeappone.repository.ItemHolder
import com.example.natifeappone.repository.ItemPreferences

interface ListView {

    fun showItems(items: List<Item>)

}

class ListPresenter(private val preferences: ItemPreferences) {

    private var view: ListView? = null

    fun attach(view: ListView) {
        this.view = view
    }

    fun getItems() {
        val items = ItemHolder.list
        view?.showItems(items)
    }

    fun saveItemId(id: Int) {
        preferences.setId(id)
    }

    fun detach() {
        view = null
    }
}