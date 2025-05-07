package com.isayevapps.alarms.presentation.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.isayevapps.alarms.R
import com.isayevapps.alarms.presentation.ui.MainActivity


class ForegroundAlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    companion object {
        private const val CHANNEL_ID = "alarm_service_channel"
        private const val NOTIFICATION_ID = 1001
        private const val ACTION_CANCEL = "com.example.alarm.ACTION_CANCEL"
    }


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action == ACTION_CANCEL) {
            stopSelf()
            return START_NOT_STICKY
        }

        val text = intent?.getStringExtra("alarm_message")
        val soundResId = intent?.getIntExtra("sound_uri", R.raw.getup_stupid) ?: R.raw.getup_stupid

        // Intent, который откроется при тапе на уведомление
        val tapIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, tapIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val cancelIntent = Intent(this, ForegroundAlarmService::class.java).apply {
            action = ACTION_CANCEL
        }
        val cancelPendingIntent = PendingIntent.getService(
            this, 0, cancelIntent, PendingIntent.FLAG_IMMUTABLE
        )

        // Собираем уведомление
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Будильник")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)  // ваша иконка
            .setContentIntent(pendingIntent)
            .addAction(               // ← вот она, кнопка "Отменить"
                R.drawable.baseline_cancel_24, // иконка кнопки (например, крестик)
                "Отменить",           // текст на кнопке
                cancelPendingIntent   // PendingIntent для cancel
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .build()

        // Переводим сервис в foreground
        startForeground(NOTIFICATION_ID, notification)
        playSound(soundResId)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Канал будильника"
            val descriptionText = "Уведомления о сработавшем будильнике"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun playSound(soundResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, soundResId).apply {
            isLooping = true
            start()
        }
    }
}