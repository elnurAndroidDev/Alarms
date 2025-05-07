package com.isayevapps.alarms.data.local

import com.isayevapps.alarms.R
import com.isayevapps.alarms.data.local.dao.AlarmDao
import com.isayevapps.alarms.data.toDomain
import com.isayevapps.alarms.data.toEntity
import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val alarmDao: AlarmDao
): LocalDataSource {
    override fun getRingtones(): List<Ringtone> {
        val ringtones = arrayListOf<Ringtone>()
        ringtones.add(Ringtone("Wake up!!!", R.drawable.gym_wakeup, R.raw.gym_wakeup.toString()))
        ringtones.add(Ringtone("Get up, stupid shit!!!", R.drawable.getup_stupid, R.raw.getup_stupid.toString()))
        return ringtones
    }

    override fun getAlarms(): Flow<List<Alarm>> {
        return alarmDao.getAlarms().map { it.map { it.toDomain() }}
    }

    override suspend fun addAlarm(alarm: Alarm) {
        return alarmDao.addAlarm(alarm.toEntity())
    }
}