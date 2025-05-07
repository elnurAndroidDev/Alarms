package com.isayevapps.alarms.domain.usecases

import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class AddAlarmUsecase(
    private val repository: Repository
) {
    suspend operator fun invoke(alarm: Alarm) {
        return repository.addAlarm(alarm)
    }
}