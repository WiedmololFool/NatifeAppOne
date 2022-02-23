package com.example.natifeappone.itemPresentation

import com.example.natifeappone.repository.ItemHolder

interface ItemView {

    fun showItem(item: Item)

}

class ItemPresenter(private var itemId: Int?) {

    private var view: ItemView? = null

    fun attach(view: ItemView) {
        this.view = view
    }

    fun detach() {
        view = null
    }

    fun getItem() {
        val item = itemId?.let { ItemHolder.getItem(it) }
        item?.let { view?.showItem(it) }
    }
}