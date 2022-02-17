package com.example.natifeappone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "com.example.natifeappone.MY_ACTION") {
            val itemId = ItemPreferences(context).getId()
            val activityIntent = if (itemId == 404) {
                Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            } else {
                Intent(context, ItemActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra(ItemActivity.KEY, itemId)
                }
            }
            context.startActivity(activityIntent)
        }
    }
}