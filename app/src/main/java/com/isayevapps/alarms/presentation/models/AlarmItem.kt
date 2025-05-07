package com.isayevapps.alarms.presentation.models

data class AlarmItem(
    val label: String,
    val time: String,
    val repeat: String,
    val vibrate: Boolean,
    val sound: String
)
