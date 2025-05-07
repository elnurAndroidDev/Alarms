package com.isayevapps.alarms.domain.repository

import com.isayevapps.alarms.domain.models.Alarm

interface AlarmScheduler {
    fun scheduleAlarm(alarm: Alarm)
    fun cancelAlarm(alarmId: Int)
}