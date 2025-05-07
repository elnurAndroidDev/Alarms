package com.isayevapps.alarms.presentation.ui.ringtone

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.isayevapps.alarms.domain.models.Ringtone

data class RingtoneRVItem(
    val name: String,
    @DrawableRes
    val image: Int?,
    val ringtoneUri: String,
    var selected: Boolean
) {
    fun toRingtone(): Ringtone {
        return Ringtone(name, image, ringtoneUri)
    }
}