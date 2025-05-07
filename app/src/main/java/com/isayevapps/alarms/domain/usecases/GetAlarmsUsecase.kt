package com.isayevapps.alarms.domain.usecases

import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAlarmsUsecase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Alarm>> {
        return repository.getAlarms()
    }
}