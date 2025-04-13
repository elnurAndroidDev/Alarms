package com.isayevapps.alarms.data.repository

import com.isayevapps.alarms.domain.local.LocalDataSource
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource): Repository {
    override fun getRingtones(): List<Ringtone> {
        return localDataSource.getRingtones()
    }
}