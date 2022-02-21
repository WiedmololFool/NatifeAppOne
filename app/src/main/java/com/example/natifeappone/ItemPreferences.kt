package com.example.natifeappone

import android.content.Context
import android.content.SharedPreferences

class ItemPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setId(id: Int) {
        sharedPreferences.edit().putInt(Constants.SHARED_PREFERENCES_ID_KEY, id).apply()
    }

    fun getId(): Int {
        return sharedPreferences.getInt(Constants.SHARED_PREFERENCES_ID_KEY, Constants.ID_DEF_VAL)
    }
}