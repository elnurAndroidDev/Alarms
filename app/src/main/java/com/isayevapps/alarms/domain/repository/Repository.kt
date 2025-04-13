package com.isayevapps.alarms.domain.repository

import com.isayevapps.alarms.domain.models.Ringtone

interface Repository {
    fun getRingtones(): List<Ringtone>
}