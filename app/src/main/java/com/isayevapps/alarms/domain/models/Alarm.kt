package com.isayevapps.alarms.domain.models

data class Alarm(
    val label: String = "",
    val time: Time = Time(0, 0),
    val ringtone: Ringtone = Ringtone(),
    val repeat: String = "Once",
    val isVibrationEnabled: Boolean = false
)