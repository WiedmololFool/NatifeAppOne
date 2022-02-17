package com.example.natifeappone

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {


    @SuppressLint("LaunchActivityFromNotification")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val itemId = intent?.getIntExtra(SERVICE_KEY, 404)
        val notificationIntent = Intent("com.example.natifeappone.MY_ACTION")
        Log.d("PutMyServiceID: ", itemId.toString())
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, App.CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_service_notification)
            .setContentTitle("Foreground Service")
            .setContentText("Tap to get last clicked Item")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
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