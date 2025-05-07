package com.isayevapps.alarms.domain.models

import androidx.annotation.IntegerRes

data class Alarm(
    val id: Int = 0,
    val label: String = "",
    val time: Time = Time(0, 0),
    val ringtone: Ringtone = Ringtone(),
    val repeat: String = "Once",
    val isEnabled: Boolean = false,
    val isVibrationEnabled: Boolean = false
)