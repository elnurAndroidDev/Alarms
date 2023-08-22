package com.isayevapps.alarms.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.isayevapps.alarms.presentation.ui.ringtone.RingtoneRVItem

data class Ringtone(
    val name: String, @DrawableRes val image: Int, @RawRes val ringtone: Int
) {
    fun toRingtoneRVItem(): RingtoneRVItem {
        return RingtoneRVItem(name, image, ringtone, false)
    }
}