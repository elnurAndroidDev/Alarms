package com.isayevapps.alarms.domain.usecases

import com.isayevapps.alarms.domain.repository.Repository

class GetRingtonesUsecase(private val repository: Repository) {
    operator fun invoke() = repository.getRingtones()
}