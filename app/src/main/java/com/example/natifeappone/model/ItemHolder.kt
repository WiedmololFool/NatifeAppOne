package com.example.natifeappone.model

object ItemHolder {

    val list: List<Item> by lazy {
        (0 until 20).map {
            Item(it," Item $it", "Description of item $it")
        }
    }

    fun getItem(id: Int): Item? {
        return list.find { it.id == id }
    }
}