package com.isayevapps.alarms.presentation.ui.ringtone

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.isayevapps.alarms.domain.models.Ringtone

data class RingtoneRVItem(
    val name: String,
    @DrawableRes
    val image: Int,
    @RawRes
    val ringtone: Int,
    var selected: Boolean
) {
    fun toRingtone(): Ringtone {
        return Ringtone(name, image, ringtone)
    }
}