package com.example.natifeappone.data.repository

import com.example.natifeappone.data.models.Item

object ItemHolder {

    val list: List<Item> by lazy {
        (0 until 20).map {
            Item(it," Item $it", "Description of item $it")
        }
    }

    fun getItem(id: Int): Item {
        return list.firstOrNull { it.id == id } ?: throw IllegalArgumentException("Item required")
    }
}