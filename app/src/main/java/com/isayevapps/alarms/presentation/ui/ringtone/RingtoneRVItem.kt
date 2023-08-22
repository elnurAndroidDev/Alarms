package com.isayevapps.alarms.presentation.ui.ringtone

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class RingtoneRVItem(
    val name: String,
    @DrawableRes
    val image: Int,
    @RawRes
    val ringtone: Int,
    var selected: Boolean
)