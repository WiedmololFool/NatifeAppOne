package com.example.natifeappone

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           val serviceChannel = NotificationChannel(
               CHANNEL_1_ID,
               "Channel 1",
               NotificationManager.IMPORTANCE_DEFAULT
           )
            serviceChannel.description = "This is Service Channel"

            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    companion object{
        const val CHANNEL_1_ID = "serviceChannel1"
    }
}