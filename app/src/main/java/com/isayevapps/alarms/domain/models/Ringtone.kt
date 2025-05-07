package com.isayevapps.alarms.domain.models

import com.isayevapps.alarms.R
import com.isayevapps.alarms.presentation.ui.ringtone.RingtoneRVItem
import java.io.Serializable

data class Ringtone(
    val name: String = "Default ringtone",
    val image: Int? = null,
    val ringtoneUri: String = R.raw.gym_wakeup.toString()
) : Serializable {
    fun toRingtoneRVItem(): RingtoneRVItem {
        return RingtoneRVItem(name, image, ringtoneUri, false)
    }
}