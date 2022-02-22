package com.example.natifeappone.presenter

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.Constants
import com.example.natifeappone.ItemListAdapter
import com.example.natifeappone.R
import com.example.natifeappone.model.ItemHolder
import com.example.natifeappone.model.ItemPreferences
import com.example.natifeappone.view.ItemFragment
import com.example.natifeappone.view.ListFragment

class ListPresenter {

    private var view: ListFragment? = null

    fun onViewAttached(view: ListFragment){
        this.view = view

        val itemPreferences = view.context?.let { ItemPreferences(it) }
        val itemListAdapter = ItemListAdapter { item ->
            itemPreferences?.setId(item.id)
            val itemFragment = ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ID_KEY, item.id)
                }
            }
            view.activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view, itemFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        view.binding?.let {
            with(it.rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemListAdapter
            }
        }
        itemListAdapter.submitList(ItemHolder.list)
    }

    fun onViewDetached(){
        view = null
    }
}