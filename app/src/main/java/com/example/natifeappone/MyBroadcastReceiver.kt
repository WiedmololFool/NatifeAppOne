package com.example.natifeappone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if ("ConnectivityManager.CONNECTIVITY_ACTION " == intent.action) {
            val noConnectivity = intent.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            )
            if (noConnectivity) {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show()
            }
        }

        if (intent.action == "com.example.natifeappone.MY_ACTION") {
//            val itemId = intent.getIntExtra("RECEIVER_ITEM_KEY", 404)
            val itemId = ItemPreferences(context).getId()
            var activityIntent = Intent(context, ItemActivity::class.java)
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activityIntent.putExtra(ItemActivity.KEY, itemId)
            if (itemId == 404){
                 activityIntent = Intent(context, MainActivity::class.java)
            }
            context.startActivity(activityIntent)
        }
    }
}