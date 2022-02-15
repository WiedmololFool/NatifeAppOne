package com.example.natifeappone

import com.example.natifeappone.model.Item

object ItemHolder {

    var list = listOf<Item>()
        private set

    fun addItem(item: Item){
        list = list + listOf(item)
    }

    fun getItem(id: Int): Item? {
        return list.find { it.id == id }
    }
}