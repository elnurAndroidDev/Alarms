package com.isayevapps.alarms.domain.local

import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getRingtones(): List<Ringtone>
    fun getAlarms(): Flow<List<Alarm>>
    suspend fun addAlarm(alarm: Alarm)
}