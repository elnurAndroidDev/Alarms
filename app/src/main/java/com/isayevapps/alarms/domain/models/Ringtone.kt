package com.isayevapps.alarms.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.isayevapps.alarms.R
import com.isayevapps.alarms.presentation.ui.ringtone.RingtoneRVItem
import java.io.Serializable

data class Ringtone(
    val name: String = "Default ringtone",
    @DrawableRes
    val image: Int = R.drawable.gym_wakeup,
    @RawRes
    val ringtone: Int = R.raw.gym_wakeup
) : Serializable {
    fun toRingtoneRVItem(): RingtoneRVItem {
        return RingtoneRVItem(name, image, ringtone, false)
    }
}