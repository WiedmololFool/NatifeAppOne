package com.example.natifeappone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Constants.MY_ACTION) {
            val itemId = ItemPreferences(context).getId()
            val activityIntent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(ItemActivity.KEY, itemId)
            }
            Log.d(Constants.MY_TAG, "MyBroadcastReceiver itemId = $itemId")
            context.startActivity(activityIntent)
        }
    }
}