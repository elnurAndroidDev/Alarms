package com.isayevapps.alarms.domain.local

import com.isayevapps.alarms.domain.models.Ringtone

interface LocalDataSource {
    fun getRingtones(): List<Ringtone>
}