package com.isayevapps.alarms.presentation.models

data class Alarm(
    val label: String = "",
    val time: Time = Time(0, 0),
    val ringtone: String = "Default ringtone",
    val repeat: String = "Once",
    val isVibrationEnabled: Boolean = false
)