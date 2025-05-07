package com.isayevapps.alarms.data

import com.isayevapps.alarms.data.local.entities.AlarmEntity
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.models.Time

fun AlarmEntity.toDomain(): Alarm {
    return Alarm(
        id,
        label,
        Time(hours, mins),
        Ringtone(name = ringtoneName, ringtoneUri = ringtoneUri),
        repeat,
        isEnabled,
        isVibrating
    )
}

fun Alarm.toEntity(): AlarmEntity {
    return AlarmEntity(
        id,
        label,
        time.hours,
        time.mins,
        ringtone.name,
        ringtone.ringtoneUri,
        repeat,
        isEnabled,
        isVibrationEnabled
    )
}