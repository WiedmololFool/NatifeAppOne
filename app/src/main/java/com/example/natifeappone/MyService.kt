package com.example.natifeappone

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val itemId = intent?.getIntExtra(SERVICE_KEY, 404)
        val item = itemId?.let { ItemHolder.getItem(it) }

        val notification = NotificationCompat.Builder(this, App.CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_service_notification)
            .setContentTitle("${item?.id} : ${item?.name}")
            .setContentText(item?.description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object{
        const val SERVICE_KEY = "ServiceKey"
    }
}