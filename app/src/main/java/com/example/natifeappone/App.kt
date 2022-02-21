package com.example.natifeappone

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.IntentFilter
import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter(Constants.MY_ACTION))
    }

    private fun createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           val serviceChannel = NotificationChannel(
               Constants.CHANNEL_1_ID,
               getString(R.string.channel_1_name),
               NotificationManager.IMPORTANCE_DEFAULT
           )
            serviceChannel.description = getString(R.string.channel_1_description)

            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}