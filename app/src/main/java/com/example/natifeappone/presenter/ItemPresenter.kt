package com.example.natifeappone.presenter

import android.widget.Toast
import com.example.natifeappone.R
import com.example.natifeappone.model.ItemHolder
import com.example.natifeappone.model.ItemPreferences
import com.example.natifeappone.view.ItemFragment

class ItemPresenter(private var itemId: Int?) {

    private var view: ItemFragment? = null

    fun onViewAttached(view: ItemFragment) {
        this.view = view
        val item = itemId?.let { ItemHolder.getItem(it) }
        val itemPreferences = view.context?.let { ItemPreferences(it) }
        Toast.makeText(
            view.context,
            view.getString(R.string.toast_chosen_item_id, itemPreferences?.getId()),
            Toast.LENGTH_SHORT
        ).show()
        view.showItem(item)
    }

    fun onViewDetached() {
        view = null
    }
}