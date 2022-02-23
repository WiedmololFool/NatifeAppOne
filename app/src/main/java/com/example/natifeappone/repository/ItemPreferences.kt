package com.example.natifeappone.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.natifeappone.Constants

class ItemPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setId(id: Int) {
        sharedPreferences.edit().putInt(Constants.SHARED_PREFERENCES_ID_KEY, id).apply()
    }

    fun getId(): Int {
        return sharedPreferences.getInt(Constants.SHARED_PREFERENCES_ID_KEY,
            Constants.ID_DEFAULT_VALUE)
    }
}