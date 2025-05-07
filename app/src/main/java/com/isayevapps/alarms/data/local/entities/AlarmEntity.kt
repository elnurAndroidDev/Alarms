package com.isayevapps.alarms.data.local.entities

import androidx.annotation.IntegerRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isayevapps.alarms.domain.models.Alarm
import com.isayevapps.alarms.domain.models.Ringtone
import com.isayevapps.alarms.domain.models.Time

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val hours: Int,
    val mins: Int,
    val ringtoneName: String,
    val ringtoneUri: String,
    val repeat: String,
    val isEnabled: Boolean,
    val isVibrating: Boolean,
)
