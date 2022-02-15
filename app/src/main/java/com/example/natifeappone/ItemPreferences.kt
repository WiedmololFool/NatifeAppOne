package com.example.natifeappone

import android.content.Context
import android.content.SharedPreferences

class ItemPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

    fun setId(id: Int) {
        sharedPreferences.edit().putInt("id", id).apply()
    }

    fun getId(): Int {
        return sharedPreferences.getInt("id", 404)
    }
}