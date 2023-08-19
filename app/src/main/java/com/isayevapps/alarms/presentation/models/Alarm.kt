package com.isayevapps.alarms.presentation.models

import java.util.Calendar

data class Alarm(
    val label: String = "",
    val time: Calendar = Calendar.getInstance(),
    val ringtone: String = "Default ringtone",
    val repeat: String = "Once",
    val isVibrationEnabled: Boolean = false
)