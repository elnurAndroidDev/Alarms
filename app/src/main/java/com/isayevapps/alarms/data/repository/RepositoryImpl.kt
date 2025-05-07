package com.isayevapps.alarms.data.repository

import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : Repository {
    override fun getRingtones(): List<Ringtone> {
        return localDataSource.getRingtones()
    }

    override fun getAlarms(): Flow<List<Alarm>> {
        return localDataSource.getAlarms()
    }

    override suspend fun addAlarm(alarm: Alarm) {
        return localDataSource.addAlarm(alarm)
    }

}