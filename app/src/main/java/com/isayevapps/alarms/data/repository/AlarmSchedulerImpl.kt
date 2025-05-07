package com.isayevapps.alarms.data.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.repository.AlarmScheduler
import com.isayevapps.alarms.presentation.alarm.AlarmReceiver

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun scheduleAlarm(alarm: Alarm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                context.startActivity(intent)
                return
            }
        }
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("alarm_id", alarm.id)
        intent.putExtra("alarm_message", alarm.label)
        intent.putExtra("sound_uri", alarm.ringtone.ringtoneUri)
        intent.putExtra("repeat", alarm.repeat)
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarm.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerAtMillis = getTriggerAtMillis(alarm.time.hours, alarm.time.mins)

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    triggerAtMillis,
                    24*60*60 * 1000L,
                    pendingIntent
                )
            } else {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    triggerAtMillis,
                    24*60*60 * 1000L,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(context, "Нет разрешения на точные будильники", Toast.LENGTH_LONG).show()
        }
    }

    override fun cancelAlarm(alarmId: Int) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmId,
                Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun getTriggerAtMillis(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance() // Получаем текущую дату и время

        // Устанавливаем время будильника на текущую дату
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // Получаем время в миллисекундах для установленной даты и времени
        var triggerAtMillis = calendar.timeInMillis

        // Проверяем, не прошло ли время будильника сегодня
        // Если время будильника уже прошло (triggerAtMillis меньше текущего времени),
        // переносим его на следующий день
        val currentTimeMillis = System.currentTimeMillis()
        if (triggerAtMillis <= currentTimeMillis) {
            calendar.add(Calendar.DAY_OF_YEAR, 1) // Добавляем один день
            triggerAtMillis = calendar.timeInMillis // Обновляем triggerAtMillis
        }

        return triggerAtMillis
    }

}